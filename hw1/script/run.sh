#!/usr/bin/env bash

cd "$(dirname "$0")/.."
pwd=`pwd`
echo pwd=${pwd}
flag=$1

function controller(){
    java -cp ${pwd}/lib/hw1.jar io.github.chenfh5.Controller ${flag}
}

function main(){
    controller
    echo "Run success at:" `date`
}

main
