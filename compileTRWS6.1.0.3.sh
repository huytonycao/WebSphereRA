#!/bin/sh

. ./TRWSAppClient6.1.0.3.sh

$ROOTDIR/java/bin/javac -classpath $JARFILESPATH -Xlint -d ./ *.java
