#!/bin/sh

# This TopRunner WebSphere Resource Analyzer is complied with
# WebSphere AppClient version 6.1.0
#
# =================================================================
# Change the values within this block.  Leave other vaules the same
#
# hostname or ip address of a ND application is running on
#ND_HOSTNAME=192.168.199.131
#ND_HOSTNAME=RH42ND
ND_HOSTNAME=172.26.76.7
# SOAP port for ND application is listening on
ND_PORT=8879

# =================================================================

./save_file.sh

# -----------------------------------------------------------------
# Should not change any value below
#
. ./TRWSAppClient6.1.0.9.sh

$ROOTDIR/java/bin/java -classpath $JARFILESPATH com.toprunner.websphere.pmi.TRWSPMI $ND_HOSTNAME $ND_PORT 1 no TRWSPMI.conf
