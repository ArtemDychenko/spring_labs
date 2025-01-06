#!/usr/bin/env bash

function main() {
    cd ./aui-movie-management/; sh ./build.sh; cd ..
    cd ./aui-director-management/; sh ./build.sh; cd ..
    cd ./aui-gateway-management/; sh ./build.sh; cd ..
    cd ./aui-ng/; sh ./build.sh; cd ..
    cd ./aui-js/; sh ./build.sh; cd ..
}

main "$@"
