package com.toprunner.websphere.pmi;

 //import com.ibm.websphere.management.AdminClient;
// import com.ibm.websphere.management.AdminClientFactory;

// import com.ibm.websphere.pmi.*;
// import com.ibm.websphere.pmi.client.*;
// import com.ibm.websphere.pmi.stat.*;
//import com.ibm.websphere.management.exception.*;

import javax.management.*;
import java.util.*;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMIVar {
  // PMI
  ObjectName perfOName = null;
  ObjectName jvmOName = null;
  ObjectName tmOName = null;
  ObjectName wlmcOName = null;
  ObjectName wlmsOName = null;

  // Monitor parameters
  String nodeName = null;
  String serverName = null;
  //String jdbcName = null;
  boolean defaulttp = false;
  boolean haManagertp = false;
  boolean mesgListener = false;
  boolean orb = false;
  boolean procDiscovery = false;
  boolean soapConnector = false;
  boolean tcpChannel = false;
  boolean webContainer = false;
  boolean transManager = false;
  boolean JVM = false;
  boolean datasource = false;
  boolean haManager = false;
  boolean wlmClient = false;
  boolean wlmServer = false;

  // is node exits
  boolean serverRunning = true;
  
  // Thread Pools. type=threadPoolModule
  // Default
  long dfCurrentThreadPool = 0;
  long dfCurrentThreadActive = 0;
  long dfThreadMaxThreshold = 0;

  // HAManager.thread.pool
  long hamCurrentThreadPool = 0;
  long hamCurrentThreadActive = 0;
  long hamThreadMaxThreshold = 0;

  // MessageListenerThreadPool
  long mlCurrentThreadPool = 0;
  long mlCurrentThreadActive = 0;
  long mlThreadMaxThreshold = 0;

  // ORB.thread.pool
  long orbCurrentThreadPool = 0;
  long orbCurrentThreadActive = 0;
  long orbThreadMaxThreshold = 0;

  // ProcessDiscovery
  long pdCurrentThreadPool = 0;
  long pdCurrentThreadActive = 0;
  long pdThreadMaxThreshold = 0;

  // SoapConnectorThreadPool
  long scCurrentThreadPool = 0;
  long scCurrentThreadActive = 0;
  long scThreadMaxThreshold = 0;

  // TCPChannel.DCS
  long dcsCurrentThreadPool = 0;
  long dcsCurrentThreadActive = 0;
  long dcsThreadMaxThreshold = 0;

  // Web Container
  long wcCurrentThreadPool = 0;
  long wcCurrentThreadActive = 0;
  long wcThreadMaxThreshold = 0;

  // JVM
  long jvmHeapMax = 0;
  long jvmCurHeapAlocated = 0;
  long jvmUsedMem = 0;
  long jvmFreeMem = 0;

  // Transaction Manager
  long tmGlobalBegunCount = 0;
  long tmLocalBegunCount = 0;
  long tmActiveCount = 0;
  long tmCommittedCount = 0;
  long tmRolledbackCount = 0;
  long tmGlobalTimeoutCount = 0;
  long tmLocalTimeoutCount = 0;
  double tmGlobalTranTime = 0.0;
  double tmLocalTranTime = 0.0;
 
  // Workload Management client
  long wlmcIIOPRequestCount = 0;
  long wlmcClientClusterUpdateCount = 0;
  double wlmcClientResponseTime = 0.0;

  // HAManager
  long hamLocalGroupCount = 0;
  long hamBulletinBoardSubjectCount = 0;
  long hamBulletinBoardSubcriptionCount = 0;
  long hamLocalBulletinBoardSubjectCount = 0;
  long hamLocalBulletinBoardSubcriptionCount = 0;
  double hamGroupStateRebuildTime = 0.0;
  double hamBulletinBoardRebuildTime = 0.0;
  
  // Workload Management server
  long wlmsIIOPRequestCount = 0;
  long wlmsStrongAffinityIIOPRequestCount = 0;
  long wlmsNoAffinityIIOPRequestCount = 0;
  long wlmsNonWLMEnabledIIOPRequestCount = 0;
  long wlmsServerClusterUpdateCount = 0;
  long wlmsWLMClientsServicedCount = 0;
  long wlmsConcurrentRequestCount = 0;
  double wlmsServerResponseTime = 0.0;

  // DataSource
  Vector <TRWSPMIDSVar> dsVct = null;
  // servlet and jsp
  Vector <TRWSPMISVar> sjVct = null;
  // Session
  Vector <TRWSPMISSVar> ssVct = null;
  
  TRWSPMIVar () {
    dsVct = new Vector <TRWSPMIDSVar> ();
    sjVct = new Vector <TRWSPMISVar> ();
    ssVct = new Vector <TRWSPMISSVar> ();
    resetData();
  }

  public void resetData () {
    TRWSPMIDSVar wspmidsvar;
    // String dsName;

    // Default
    dfCurrentThreadPool = 0;
    dfCurrentThreadActive = 0;
    dfThreadMaxThreshold = 0;

    // HAManager.thread.pool
    hamCurrentThreadPool = 0;
    hamCurrentThreadActive = 0;
    hamThreadMaxThreshold = 0;

    // MessageListenerThreadPool
    mlCurrentThreadPool = 0;
    mlCurrentThreadActive = 0;
    mlThreadMaxThreshold = 0;

    // ORB.thread.pool
    orbCurrentThreadPool = 0;
    orbCurrentThreadActive = 0;
    orbThreadMaxThreshold = 0;

    // ProcessDiscovery
    pdCurrentThreadPool = 0;
    pdCurrentThreadActive = 0;
    pdThreadMaxThreshold = 0;

    // SoapConnectorThreadPool
    scCurrentThreadPool = 0;
    scCurrentThreadActive = 0;
    scThreadMaxThreshold = 0;

    // TCPChannel.DCS
    dcsCurrentThreadPool = 0;
    dcsCurrentThreadActive = 0;
    dcsThreadMaxThreshold = 0;

    // Web Container
    wcCurrentThreadPool = 0;
    wcCurrentThreadActive = 0;
    wcThreadMaxThreshold = 0;

    // JVM
    jvmHeapMax = 0;
    jvmCurHeapAlocated = 0;
    jvmUsedMem = 0;
    jvmFreeMem = 0;

    // Transaction Manager
    tmGlobalBegunCount = 0;
    tmLocalBegunCount = 0;
    tmActiveCount = 0;
    tmCommittedCount = 0;
    tmRolledbackCount = 0;
    tmGlobalTimeoutCount = 0;
    tmLocalTimeoutCount = 0;
    tmGlobalTranTime = 0.0;
    tmLocalTranTime = 0.0;

    // Workload Management client
    wlmcIIOPRequestCount = 0;
    wlmcClientClusterUpdateCount = 0;
    wlmcClientResponseTime = 0.0;

    // HAManager
    hamLocalGroupCount = 0;
    hamBulletinBoardSubjectCount = 0;
    hamBulletinBoardSubcriptionCount = 0;
    hamLocalBulletinBoardSubjectCount = 0;
    hamLocalBulletinBoardSubcriptionCount = 0;
    hamGroupStateRebuildTime = 0.0;
    hamBulletinBoardRebuildTime = 0.0;

    // Workload Management server
    wlmsIIOPRequestCount = 0;
    wlmsStrongAffinityIIOPRequestCount = 0;
    wlmsNoAffinityIIOPRequestCount = 0;
    wlmsNonWLMEnabledIIOPRequestCount = 0;
    wlmsServerClusterUpdateCount = 0;
    wlmsWLMClientsServicedCount = 0;
    wlmsConcurrentRequestCount = 0;
    wlmsServerResponseTime = 0.0;

    if (dsVct != null) {
      for (int i = 0; i < dsVct.size(); i++) {
        wspmidsvar = dsVct.elementAt(i);
        // dsName = wspmidsvar.getDataSourceName();
        wspmidsvar.resetData(); 
      }
    }
  }

  // PMI
  public void setPerfOName(ObjectName val){
    perfOName = val;
  }

  public void setJVMOName(ObjectName val){
    jvmOName = val;
  }

  public void setTMName(ObjectName val){
    tmOName = val;
  }

  public void setWLMCName(ObjectName val){
    wlmcOName = val;
  }

  public ObjectName getPerfOName(){
    return perfOName;
  }

  public ObjectName getJVMOName(){
    return jvmOName;
  }

  public ObjectName getTMOName(){
    return tmOName;
  }

  public ObjectName getWLMCOName(){
    return wlmcOName;
  }

  // Monitor parameters
  public void setNodeName(String val) {
    nodeName = val;
  }
 
  public void setServerName(String val) {
    serverName = val;
  }
 
  //public void setJDBCName(String val) {
  //  jdbcName = val;
  //}
 
  public void setMonitorDefault(boolean val) {
    defaulttp = val;
  }
 
  public void setMonitorHAManagerThreadPool(boolean val) {
    haManagertp = val;
  }
 
  public void setMonitorMessageListener(boolean val) {
    mesgListener = val;
  }
 
  public void setMonitorORB(boolean val) {
    orb = val;
  }
 
  public void setMonitorProcessDiscovery(boolean val) {
    procDiscovery = val;
  }
 
  public void setMonitorSOAPConnector(boolean val) {
    soapConnector = val;
  }
 
  public void setMonitorTCPChannelDCS(boolean val) {
    tcpChannel = val;
  }
 
  public void setMonitorWebContainer(boolean val) {
    webContainer = val;
  }
 
  public void setMonitorJVM(boolean val) {
    JVM = val;
  }
 
  public void setMonitorDataSource(boolean val) {
    datasource = val;
  }
 
  public void setMonitorTransactionManager(boolean val) {
    transManager = val;
  }
 
  public void setMonitorWLMC(boolean val) {
    wlmClient = val;
  }
 
  public void setMonitorWLMS(boolean val) {
    wlmServer = val;
  }
 
  public void setMonitorHAManager(boolean val) {
    haManager = val;
  }
 
  public String getNodeName() {
    return nodeName;
  }

  public String getServerName() {
    return serverName;
  }

  //public String getJDBCName() {
  //  return jdbcName;
  //}

  public boolean getMonitorDefault() {
    return defaulttp;
  }
 
  public boolean getMonitorHAManagerThreadPool() {
    return haManagertp;
  }
 
  public boolean getMonitorMessageListener() {
    return mesgListener;
  }
 
  public boolean getMonitorORB() {
    return orb;
  }
 
  public boolean getMonitorProcessDiscovery() {
    return procDiscovery;
  }
 
  public boolean getMonitorSOAPConnector() {
    return soapConnector;
  }
 
  public boolean getMonitorTCPChannelDCS() {
    return tcpChannel;
  }
 
  public boolean getMonitorWebContainer() {
    return webContainer;
  }
 
  public boolean getMonitorJVM() {
    return JVM;
  }
 
  public boolean getMonitorDataSource() {
    return datasource;
  }
 
  // default thread pool
  public void setDFCurrentThreadPool(long val) {
    dfCurrentThreadPool = val;
  }

  public void setDFCurrentThreadActive(long val) {
    dfCurrentThreadActive = val;
  }

  public void setDFThreadMaxThreshold(long val) {
    dfThreadMaxThreshold = val;
  }

  public long getDFCurrentThreadPool() {
    return dfCurrentThreadPool;
  }

  public long getDFCurrentThreadActive() {
    return dfCurrentThreadActive;
  }

  public long getDFThreadMaxThreshold() {
    return dfThreadMaxThreshold;
  }

  // HAManager thread pool
  public void setHAMCurrentThreadPool(long val) {
    hamCurrentThreadPool = val;
  }

  public void setHAMCurrentThreadActive(long val) {
    hamCurrentThreadActive = val;
  }

  public void setHAMThreadMaxThreshold(long val) {
    hamThreadMaxThreshold = val;
  }

  public long getHAMCurrentThreadPool() {
    return hamCurrentThreadPool;
  }

  public long getHAMCurrentThreadActive() {
    return hamCurrentThreadActive;
  }

  public long getHAMThreadMaxThreshold() {
    return hamThreadMaxThreshold;
  }

  // MessageListener thread pool
  public void setMLCurrentThreadPool(long val) {
    mlCurrentThreadPool = val;
  }

  public void setMLCurrentThreadActive(long val) {
    mlCurrentThreadActive = val;
  }

  public void setMLThreadMaxThreshold(long val) {
    mlThreadMaxThreshold = val;
  }

  public long getMLCurrentThreadPool() {
    return mlCurrentThreadPool;
  }

  public long getMLCurrentThreadActive() {
    return mlCurrentThreadActive;
  }

  public long getMLThreadMaxThreshold() {
    return mlThreadMaxThreshold;
  }

  // ORB thread pool
  public void setORBCurrentThreadPool(long val) {
    orbCurrentThreadPool = val;
  }

  public void setORBCurrentThreadActive(long val) {
    orbCurrentThreadActive = val;
  }

  public void setORBThreadMaxThreshold(long val) {
    orbThreadMaxThreshold = val;
  }

  public long getORBCurrentThreadPool() {
    return orbCurrentThreadPool;
  }

  public long getORBCurrentThreadActive() {
    return orbCurrentThreadActive;
  }

  public long getORBThreadMaxThreshold() {
    return orbThreadMaxThreshold;
  }

  // ProcessDiscovery thread pool
  public void setPDCurrentThreadPool(long val) {
    pdCurrentThreadPool = val;
  }

  public void setPDCurrentThreadActive(long val) {
    pdCurrentThreadActive = val;
  }

  public void setPDThreadMaxThreshold(long val) {
    pdThreadMaxThreshold = val;
  }

  public long getPDCurrentThreadPool() {
    return pdCurrentThreadPool;
  }

  public long getPDCurrentThreadActive() {
    return pdCurrentThreadActive;
  }

  public long getPDThreadMaxThreshold() {
    return pdThreadMaxThreshold;
  }

  // SoapConnector thread pool
  public void setSCCurrentThreadPool(long val) {
    scCurrentThreadPool = val;
  }

  public void setSCCurrentThreadActive(long val) {
    scCurrentThreadActive = val;
  }

  public void setSCThreadMaxThreshold(long val) {
    scThreadMaxThreshold = val;
  }

  public long getSCCurrentThreadPool() {
    return scCurrentThreadPool;
  }

  public long getSCCurrentThreadActive() {
    return scCurrentThreadActive;
  }

  public long getSCThreadMaxThreshold() {
    return scThreadMaxThreshold;
  }

  // DCS thread pool
  public void setDCSCurrentThreadPool(long val) {
    dcsCurrentThreadPool = val;
  }

  public void setDCSCurrentThreadActive(long val) {
    dcsCurrentThreadActive = val;
  }

  public void setDCSThreadMaxThreshold(long val) {
    dcsThreadMaxThreshold = val;
  }

  public long getDCSCurrentThreadPool() {
    return dcsCurrentThreadPool;
  }

  public long getDCSCurrentThreadActive() {
    return dcsCurrentThreadActive;
  }

  public long getDCSThreadMaxThreshold() {
    return dcsThreadMaxThreshold;
  }

  // Web Container thread pool
  public void setWCCurrentThreadPool(long val) {
    wcCurrentThreadPool = val;
  }

  public void setWCCurrentThreadActive(long val) {
    wcCurrentThreadActive = val;
  }

  public void setWCThreadMaxThreshold(long val) {
    wcThreadMaxThreshold = val;
  }

  public long getWCCurrentThreadPool() {
    return wcCurrentThreadPool;
  }

  public long getWCCurrentThreadActive() {
    return wcCurrentThreadActive;
  }

  public long getWCThreadMaxThreshold() {
    return wcThreadMaxThreshold;
  }

  // JVM
  public void setJVMHeapMax(long val) {
    jvmHeapMax = val;
  }

  public void setJVMCurHeapAlocated(long val) {
    jvmCurHeapAlocated = val;
  }

  public void setJVMUsedMem(long val) {
    jvmUsedMem = val;
  }

  public void setJVMFreeMem(long val) {
    jvmFreeMem = val;
  }

  public long getJVMHeapMax() {
    return jvmHeapMax;
  }

  public long getJVMCurHeapAlocated() {
    return jvmCurHeapAlocated;
  }

  public long getJVMUsedMem() {
    return jvmUsedMem;
  }

  public long getJVMFreeMem() {
    return jvmFreeMem;
  }

  // Transaction Manager
  public long getTMGlobalBegunCount() {
    return tmGlobalBegunCount;
  }

  public void setTMGlobalBegunCount(long val) {
    tmGlobalBegunCount = val;
  }

  public long getTMtmLocalBegunCount() {
    return tmLocalBegunCount;
  }

  public void setTMtmLocalBegunCount(long val) {
    tmLocalBegunCount = val;
  }

  public long getTMActiveCount() {
    return tmActiveCount;
  }

  public void setTMActiveCount(long val) {
    tmActiveCount = val;
  }

  public long getTMCommittedCount() {
    return tmCommittedCount;
  }

  public void setTMCommittedCount(long val) {
    tmCommittedCount = val;
  }

  public long getTMRolledbackCount() {
    return tmRolledbackCount;
  }

  public void setTMRolledbackCount(long val) {
    tmRolledbackCount = val;
  }

  public long getTMGlobalTimeoutCount() {
    return tmGlobalTimeoutCount;
  }

  public void setTMGlobalTimeoutCount(long val) {
    tmGlobalTimeoutCount = val;
  }

  public long getTMLocalTimeoutCount() {
    return tmLocalTimeoutCount;
  }

  public void setTMLocalTimeoutCount(long val) {
    tmLocalTimeoutCount = val;
  }

  public double getTMGlobalTranTime() {
    return tmGlobalTranTime;
  }

  public void setTMGlobalTranTime(double val) {
    tmGlobalTranTime = val;
  }

  public double getTMLocalTranTime() {
    return tmLocalTranTime;
  }

  public void setTMLocalTranTime(double val) {
    tmLocalTranTime = val;
  }

  // DataSource
  void setDS(TRWSPMIDSVar var) {
    dsVct.add(var);
  } 
  
  public Vector <TRWSPMIDSVar> getDS() {
    return dsVct; 
  }

  // Servlet Jsp
  boolean setServletJsp(TRWSPMISVar var) {
    // String name;
    TRWSPMISVar vct;

    if (sjVct == null)
      return false;

    for (int i = 0; i < sjVct.size(); i++) {
      vct = sjVct.elementAt(i); 

      if (vct == null)
        return false;

      if ((vct.getServletOrJsp()).equals(var.getServletOrJsp()))
        return false;
    }

    sjVct.add(var);

    return true;
  } 
  
  public Vector <TRWSPMISVar> getServletJsp() {
    return sjVct; 
  }

  // Sessions
  boolean setSession(TRWSPMISSVar var) {
    // String name;
    TRWSPMISSVar vct;

    if (ssVct == null)
      return false;

    for (int i = 0; i < ssVct.size(); i++) {
      vct = ssVct.elementAt(i);

      if (vct == null)
        return false;

      if ((vct.getAppName()).equals(var.getAppName()))
        return false;
    }

    ssVct.add(var);

    return true;
  }

  public Vector <TRWSPMISSVar> getSession() {
    return ssVct;
  }

  // is node exists
  public void setServerRunning(boolean val) {
    serverRunning = val;
  }

  public boolean isServerRunning() {
    return serverRunning;
  }

  // Workload Management client
  public long getWLMCIIOPRequestCount() {
    return wlmcIIOPRequestCount;
  }

  public long getWLMCClientClusterUpdateCount() {
    return wlmcClientClusterUpdateCount;
  }

  public double getWLMCClientResponseTime() {
    return wlmcClientResponseTime;
  }

  public void setWLMCIIOPRequestCount(long val) {
    wlmcIIOPRequestCount = val;
  }

  public void setWLMCClientClusterUpdateCount(long val) {
    wlmcClientClusterUpdateCount = val;
  }

  public void setWLMCClientResponseTime(double val) {
    wlmcClientResponseTime = val;
  }

  // HAManager
  public void setHAMLocalGroupCount(long val) {
    hamLocalGroupCount = val;
  }

  public void setHAMBulletinBoardSubjectCount(long val) {
    hamBulletinBoardSubjectCount = val;
  }

  public void setHAMBulletinBoardSubcriptionCount(long val) {
    hamBulletinBoardSubcriptionCount = val;
  }

  public void setHAMLocalBulletinBoardSubjectCount(long val) {
    hamLocalBulletinBoardSubjectCount = val;
  }

  public void setHAMLocalBulletinBoardSubcriptionCount(long val) {
    hamLocalBulletinBoardSubcriptionCount = val;
  }

  public void setHAMGroupStateRebuildTime(double val) {
    hamGroupStateRebuildTime = val;
  }

  public void setHAMBulletinBoardRebuildTime(double val) {
    hamBulletinBoardRebuildTime = val;
  }

  public long getHAMLocalGroupCount() {
    return hamLocalGroupCount;
  }

  public long getHAMBulletinBoardSubjectCount() {
    return hamBulletinBoardSubjectCount;
  }

  public long getHAMBulletinBoardSubcriptionCount() {
    return hamBulletinBoardSubcriptionCount;
  }

  public long getHAMLocalBulletinBoardSubjectCount() {
    return hamLocalBulletinBoardSubjectCount;
  }

  public long getHAMLocalBulletinBoardSubcriptionCount() {
    return hamLocalBulletinBoardSubcriptionCount;
  }

  public double getHAMGroupStateRebuildTime() {
    return hamGroupStateRebuildTime;
  }

  public double getHAMBulletinBoardRebuildTime() {
    return hamBulletinBoardRebuildTime;
  }
}

