export class UiUtils {

    initInputs() {
        document.querySelectorAll('.form-outline').forEach((formOutline) => {
            new mdb.Input(formOutline).init();// Variable not imported into module but will be visible in JS engine.
        });
    }

}
