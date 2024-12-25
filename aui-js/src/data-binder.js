export class DataBinder {

    /**
     * Listeners can only be removed with event type and listener reference. There is no way to list listeners. Storing
     * them or cloning node (listeners are not cloned) would be troublesome. With controller, it is possible to send
     * signal for aborting all listeners bound to the same controller.
     *
     * @type {AbortController}
     */
    controller = new AbortController();

    /**
     * Detect changes in model. When detects layout elements based on non-existing component values, it removes them.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    detectChanges(component, parent) {
        parent.querySelectorAll('[data-item]').forEach(node => {
            if (!this.evaluateReadValue(component, node.getAttribute('data-item'))) {
                node.remove();
            }
        });
        this.render(component, parent);
    }


    /**
     * Updates component data with input values.
     *
     * @param {Object} component component with data to be updated
     * @param {Element} parent node from which all children will be used
     */
    update(component, parent) {
        parent.querySelectorAll('[data-value]').forEach(node => {
            if (node.tagName === 'INPUT' && node.getAttribute('type') === 'radio') {
                // Exclude all radio inputs.
                return;
            }
            this.evaluateWriteValue(component, node.getAttribute('data-value'), node.value);
        });

        parent.querySelectorAll('[data-text]').forEach(node => {
            this.evaluateWriteValue(component, node.getAttribute('data-text'), node.value);
        });

        parent.querySelectorAll('[data-selected-value]').forEach(node => {
            if (node.tagName === 'INPUT' && node.getAttribute('type') === 'radio' && !node.checked) {
                // Exclude radio inputs that are unchecked.
                return;
            }
            this.evaluateWriteValue(component, node.getAttribute('data-selected-value'), node.value);
        });
    }

    /**
     * Updates all nodes with data-* attributes. Firstly it replaces all nodes with data-for attribute with a number of
     * nodes based on component data. Then it updates components values based on all other data-* attributes.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    render(component, parent) {
        this.controller.abort();
        this.controller = new AbortController();
        this.renderDataFor(component, parent);
        this.renderDataText(component, parent);
        this.renderDataSrc(component, parent);
        this.renderDataHref(component, parent);
        this.renderDataValue(component, parent);
        this.renderDataReset(component, parent);
        this.renderDataSubmit(component, parent);
        this.renderDataClick(component, parent);
    }

    /**
     * Updates all nodes with data-for attribute replacing them with a number of instances according to values from
     * the component.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataFor(component, parent) {
        parent.querySelectorAll('[data-for]').forEach(node => {
            // For syntax uses ':' to separate iterating variable from iterable list.
            let [varEl, forEl] = node.getAttribute('data-for').split(':');
            varEl = varEl.trim();
            forEl = forEl.trim();

            let forValue = this.evaluateReadValue(component, forEl);
            // Arrays are also objects.
            if (typeof forValue === 'object') {
                forValue.forEach((entry, index) => {
                    let clonedNode = node.cloneNode(true);
                    clonedNode.removeAttribute('data-for');
                    let baseEl = this.resolveIndex(forEl, index);
                    clonedNode.setAttribute('data-item', baseEl);
                    this.updateScopedExpressions(clonedNode, baseEl, varEl)
                    node.parentNode.append(clonedNode);
                });
            }
            node.remove();
        });
    }

    /**
     * In case of nodes with for-each loop, there can be used locally scoped variables used for iterating in a for-each
     * list. All nodes in parent which containing expression suing local variable are updated to use component variable.
     *
     * @param {Element} parent node from which all children will be updated
     * @param {string} baseEl expression which can be evaluated to component object
     * @param {string} varEl name of local variable
     */
    updateScopedExpressions(parent, baseEl, varEl) {
        parent.querySelectorAll('*').forEach(node => {
            for (let i = 0; i < node.attributes.length; i++) {
                if (node.attributes[i].name.startsWith('data-')) {
                    let el = node.attributes[i].value;
                    el = this.resolveBaseEl(el, varEl, baseEl);
                    node.setAttribute(node.attributes[i].name, el);
                }
            }
        });
    }


    /**
     * Updates nodes with data-text attribute with values from component. The data-text expression evaluation is used
     * as text node inserted into original node with data-text attribute.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataText(component, parent) {
        parent.querySelectorAll('[data-text]').forEach(node => {
            let textEl = node.getAttribute('data-text');
            const textNode = document.createTextNode(this.evaluateReadValue(component, textEl));
            if (node.firstChild && node.firstChild.nodeType === Node.TEXT_NODE) {
                node.replaceChild(textNode, node.firstChild);
            } else {
                node.appendChild(textNode);
            }
        });
    }

    /**
     * Updates nodes with data-src attribute with values from this component. The data-src expression evaluation is used
     * as src attribute value.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataSrc(component, parent) {
        parent.querySelectorAll('[data-src]').forEach(node => {
            let srcEl = node.getAttribute('data-src');
            node.src = this.evaluateReadValue(component, srcEl);
        });
    }

    /**
     * Updates nodes with data-href attribute with values from this component. The data-href expression evaluation is
     * used as href attribute value.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataHref(component, parent) {
        parent.querySelectorAll('[data-href]').forEach(node => {
            let href = node.getAttribute('data-href');
            node.href = this.evaluateReadValue(component, href);
        });
    }

    /**
     * Updates nodes with data-value attribute with values from this component. The data-value expression evaluation is
     * used as value attribute value. Additionally, if node contains data-selected-value, it is used to determine if
     * node defaultChecked property should be set (for radio buttons etc.).
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataValue(component, parent) {
        parent.querySelectorAll('[data-value]').forEach(node => {
            let valueEl = node.getAttribute('data-value');
            node.setAttribute('value', this.evaluateReadValue(component, valueEl));
            if (node.hasAttribute('data-selected-value')) {
                node.defaultChecked = node.value === this.evaluateReadValue(component, node.getAttribute('data-selected-value'));
            }
        });
    }

    /**
     * Updates nodes with data-click attribute with values from this component. The data-click expression evaluation is
     * used as click callback.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataClick(component, parent) {
        parent.querySelectorAll('[data-click]').forEach(node => {
            let {
                param,
                method
            } = this.extractMethodAndParam(component, node.getAttribute('data-click'));
            node.addEventListener('click', () => {
                method(param);
            }, {signal: this.controller.signal});
        });
    }

    /**
     * Updates nodes with data-reset attribute with values from this component. The data-reset expression evaluation is
     * used as reset callback.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataReset(component, parent) {
        parent.querySelectorAll('[data-reset]').forEach(node => {
            let {
                param,
                method
            } = this.extractMethodAndParam(component, node.getAttribute('data-reset'));
            node.addEventListener('reset', () => {
                // Leave default so form resets to original values.
                method(param);
            }, {signal: this.controller.signal});
        });
    }

    /**
     * Updates nodes with data-submit attribute with values from this component. The data-submit expression evaluation
     * is used as submit callback.
     *
     * @param {Object} component component with data to be used
     * @param {Element} parent node from which all children will be updated
     */
    renderDataSubmit(component, parent) {
        parent.querySelectorAll('[data-submit]').forEach(node => {
            let {
                param,
                method
            } = this.extractMethodAndParam(component, node.getAttribute('data-submit'));
            node.addEventListener('submit', (event) => {
                // Prevent default to prevent sending form.
                event.preventDefault();
                method(param);
            }, {signal: this.controller.signal});
        });
    }

    /**
     * Extracts method and parameter for method call expression.
     *
     * @param {Object} component component with data to be used
     * @param {string} actionEl action expression
     * @returns {{method: Function, param: Object|string}}
     */
    extractMethodAndParam(component, actionEl) {
        let [methodEl, paramEl] = actionEl.split('(');
        methodEl = methodEl.trim() + '}';
        paramEl = '{' + paramEl.replace(')', '').trim();
        let param = this.evaluateReadValue(component, paramEl);
        let method = this.evaluateReadValue(component, methodEl);
        return {param, method};
    }

    /**
     * Adds index to expression.
     *
     * @param {string} el expression
     * @param {number} index index
     * @returns {string} indexed expression
     */
    resolveIndex(el, index) {
        return el.replace('}', `.${index}}`);
    }

    /**
     * If expression contains scoped variable, it is replaced with base expression. Useful with for each where
     * iterating variable is scoped variable.
     *
     * @param {string} el expression (with {})
     * @param {string} varEl scoped variable name (without {})
     * @param {string} baseEl expression to replace scoped variable (with {})
     * @returns {string} expression with scoped variable replaced by base expression
     */
    resolveBaseEl(el, varEl, baseEl) {
        // Regular expression /regexp/ syntax does no allows for using variables inside.
        const regexp = el.indexOf("(") > -1
            ? new RegExp(`({.+\\()(${varEl})([.)].*})`)
            : new RegExp(`({)(${varEl})([.}].*)`);
        return el.replace(regexp, '$1' + baseEl.match(/{(.+)}/)[1] + '$3');
    }

    /**
     * Evaluates expression and returns value from the component. Objects are returned as they are without any
     * modifications. For functions, new functions with this keyword bound to component are returned. String values are
     * inserted in place or original expression (for example: '/api/characters/{id}' can result with
     * `/api/characters/12d92b4e-8592-4600-9d26-0a086178a4ad').
     *
     * @param {Object} component component with data to be used
     * @param {string} el expression
     * @returns {Object|Function|string} value from component pointed by expression
     */
    evaluateReadValue(component, el) {
        if (el === '{}') {
            return undefined;
        }
        let value = component;
        let fields = el.match(/{([a-zA-Z0-9.]+)}/)[1].split('.');
        while (fields.length) {
            value = value[fields.shift()];
        }
        // Creates new function with this keyword bound to component from which it should be called.
        if (typeof value === 'function') {
            value = value.bind(component);
        }
        // For string values, they are inserted in place of original expression.
        if (typeof value === 'string') {
            value = el.replaceAll(/{[a-zA-Z0-9.]+}/g, value);
        }
        return value;
    }

    /**
     * Evaluates expression and returns value to the component. If original values is number, then new value is
     * converted. For everything else original string value is used.
     *
     * @param {Object} component component with data to be updated
     * @param {string} el expression
     * @param {string} value new value
     */
    evaluateWriteValue(component, el, value) {
        let fields = el.match(/{([a-zA-Z0-9.]+)}/)[1].split('.');
        while (fields.length - 1) {
            component = component[fields.shift()];
        }
        let property = fields.shift();
        if (typeof component[property] === 'number') {
            component[property] = Number(value);
        } else {
            component[property] = value;
        }
    }

}
