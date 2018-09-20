#!/usr/bin/env bash
cd `dirname $0`
pwd=`pwd`
flag=$1

function controller(){
    java -cp ${pwd}/../target/hw1.jar io.github.chenfh5.Controller ${flag}
}

function mvnBuild(){
    cd ${pwd}/..
    mvn clean package
}

function main(){
    jarFile=$pwd/../target/hw1.jar
    if [ -f "$jarFile" ]
    then
        controller
    else
        echo "$jarFile not found."
        mvnBuild
        controller
    fi
    echo "Run success at:" `date`
}

main
