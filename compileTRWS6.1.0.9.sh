#!/bin/sh

. ./TRWSAppClient6.1.0.9.sh

#echo $JARFILESPATH

#echo "$ROOTDIR/java/bin/javac -classpath $JARFILESPATH -Xlint -d ./ *.java"
$ROOTDIR/java/bin/javac -classpath $JARFILESPATH -Xlint -d ./ *.java
