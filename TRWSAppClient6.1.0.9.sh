#!/bin/sh

# ##################################################################
# if you have a different location, please change the java directory
# bellow
# ##################################################################
ROOTDIR=/home/tonycao/IBM/WebSphere/AppClient_6.1.0.9
export ROOTDIR;

. ./TRsed.sh

# All jar files from AppClient directory
JARFILESPATH=$CLASSPATH:.:$TRCLASSPATH
export JARFILESPATH
