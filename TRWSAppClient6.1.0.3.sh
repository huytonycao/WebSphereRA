#!/bin/sh

# root directory for WebSphere AppClient
ROOTDIR=/home/tonycao/IBM/WebSphere/AppClient_6.1.0.3
export ROOTDIR;

. ./TRsed.sh

# All jar files from AppClient directory
JARFILESPATH=$CLASSPATH:.:$TRCLASSPATH
export JARFILESPATH
