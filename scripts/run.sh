#!/bin/bash

cd ../
mvn package

TYPE=$1
JAR_PATH="target/task-1.0-SNAPSHOT.jar"
ONE_PROCESS="-one-process"
TWO_PROCESS="-two-process"
HELP="-help"

if [[ "$TYPE" = "$ONE_PROCESS" ]]; then
   java -jar ${JAR_PATH} -one-process
elif [[ "$TYPE" = "$TWO_PROCESS" ]]; then
    java -jar ${JAR_PATH} -server &
    java -jar ${JAR_PATH} -client
elif [[ "$TYPE" = "$HELP" ]]; then
    echo "Use ./run.sh $ONE_PROCESS for running in the same java process"
    echo "Use ./run.sh $TWO_PROCESS for running in the same java process"
else
    echo "Invalid command. ./run.sh -help shows all available commands"
fi