#!/usr/bin/env bash
cd `dirname $0`
pwd=`pwd`

java -cp $pwd/../target/hw1.jar io.github.chenfh5.Controller 99
