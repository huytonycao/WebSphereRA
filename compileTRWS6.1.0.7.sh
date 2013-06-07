#!/bin/sh

. ./TRWSAppClient6.1.0.7.sh

$ROOTDIR/java/bin/javac -classpath $JARFILESPATH -Xlint -d ./ *.java
