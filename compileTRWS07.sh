#!/bin/sh

ROOTDIR=/home/dj810694/IBM/WebSphere/AppClient07
#use this to look at thread dump
#javac -J-agentlib:hprof=heap=sites -classpath \

#use this with no thread dump
#javac  -classpath \

javac -classpath \
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
$ROOTDIR/plugins/org.eclipse.core.runtime_3.2.0.v20060603.jar:\
$ROOTDIR/plugins/com.ibm.cds_2.0.1.200608081353.jar:\
$ROOTDIR/plugins/com.ibm.ws.runtime.gateway_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.runtime.compatibility_3.1.100.v20060603.jar:\
$ROOTDIR/plugins/com.ibm.events.client_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.security.crypto_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.core.variables_3.1.100.v20060605.jar:\
$ROOTDIR/plugins/com.ibm.uddi.client_1.0.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.sib.client_2.0.0.jar:\
$ROOTDIR/plugins/org.eclipse.jdt.core_3.2.0.v_671.jar:\
$ROOTDIR/plugins/com.ibm.ws.bootstrap_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.sib.utils_2.0.0.jar:\
$ROOTDIR/plugins/org.eclipse.osgi_3.2.1.R32x_v20060919.jar:\
$ROOTDIR/plugins/com.ibm.ws.debug.osgi_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.wsspi.extension_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.emf_2.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.wccm_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.text_3.2.0.v20060605-1400.jar:\
$ROOTDIR/plugins/com.ibm.ws.j2ee.client_6.1.0.jar:\
$ROOTDIR/plugins/com.ibm.ws.wccm.compatibility_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.update.configurator_3.2.1.v20092006.jar:\
$ROOTDIR/plugins/com.ibm.ws.migration_6.1.0.jar:\
$ROOTDIR/plugins/org.eclipse.ant.core_3.1.100.v20060531.jar:\
$ROOTDIR/plugins/org.eclipse.core.resources_3.2.1.R32x_v20060914.jar:\
$ROOTDIR/lib/webadmin/management.jar:\
. \
-Xlint -d ./ *.java
