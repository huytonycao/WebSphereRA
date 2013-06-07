#!/bin/sh

# This TopRunner WebSphere Resource Analyzer is complied with 
# WebSphere AppClient version 6.1.0
#
# =================================================================
# Change the values within this block.  Leave other vaules the same
#
# hostname or ip address of a ND application is running on
#ND_HOSTNAME=192.168.199.131
ND_HOSTNAME=RH42ND
# SOAP port for ND application is listening on
ND_PORT=8879

# root directory for WebSphere AppClient
ROOTDIR=/opt/IBM/WebSphere/AppClient
# =================================================================

# -----------------------------------------------------------------
# Should not change any value below
#
PATH=$PATH:$ROOTDIR/java/bin
CLASSPATH=$ROOTDIR/lib:$ROOTDIR/plugins:.

export PATH
export CLASSPATH

$ROOTDIR/java/bin/java -classpath \
$ROOTDIR/lib/startup.jar:\
$ROOTDIR/lib/mail-impl.jar:\
$ROOTDIR/lib/j2ee.jar:\
$ROOTDIR/lib/aspectjrt.jar:\
$ROOTDIR/lib/activation-impl.jar:\
$ROOTDIR/lib/wsatlib.jar:\
$ROOTDIR/lib/physicalrep.jar:\
$ROOTDIR/lib/marshall.jar:\
$ROOTDIR/lib/bootstrap.jar:\
$ROOTDIR/lib/nif.jar:\
$ROOTDIR/lib/installxml.jar:\
$ROOTDIR/lib/installver.jar:\
$ROOTDIR/lib/commandlineutils.jar:\
$ROOTDIR/lib/sib.api.jmsra.rar:\
$ROOTDIR/lib/lmproxy.jar:\
$ROOTDIR/lib/urlprotocols.jar:\
$ROOTDIR/lib/launchclient.jar:\
$ROOTDIR/lib/serviceadapter.jar:\
$ROOTDIR/plugins/com.ibm.ws.runtime_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.runtime.dist_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.runtime_3.1.2.jar:\
$ROOTDIR/plugins/com.ibm.cds_1.0.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.runtime.gateway_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.runtime.compatibility_3.1.0.jar:\
$ROOTDIR/plugins/com.ibm.events.client_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.security.crypto_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.variables_3.1.0.jar:\
$ROOTDIR/plugins/com.ibm.uddi.client_1.0.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.sib.client_2.0.0.jar:\
$ROOTDIR/plugins/org.eclipse.jdt.core_3.1.2.jar:\
$ROOTDIR/plugins/com.ibm.ws.bootstrap_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.sib.utils_2.0.0.jar:\
$ROOTDIR/plugins/org.eclipse.osgi_3.1.2.jar:\
$ROOTDIR/plugins/com.ibm.ws.debug.osgi_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.wsspi.extension_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.team.core_3.1.1.jar:\
$ROOTDIR/plugins/com.ibm.ws.emf_2.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.wccm_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.text_3.1.1.jar:\
$ROOTDIR/plugins/com.ibm.ws.j2ee.client_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.wccm.compatibility_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.update.configurator_3.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.migration_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.ant.core_3.1.1.jar:\
$ROOTDIR/plugins/com.ibm.ws.runtime_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.resources_3.1.2.jar:\
. \
 com.toprunner.websphere.pmi.TRWSPMI $ND_HOSTNAME $ND_PORT 1 yes TRWSPMI.conf
