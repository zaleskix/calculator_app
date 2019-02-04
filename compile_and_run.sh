#!/usr/bin/env bash

mvn package -DskipTests
java -jar ./target/calculator-1.0.jar
echo "Click any key to exit "
read end