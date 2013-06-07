package com.toprunner.websphere.pmi;

// import com.ibm.websphere.management.AdminClient;
// import com.ibm.websphere.management.AdminClientFactory;

// import com.ibm.websphere.pmi.*;
// import com.ibm.websphere.pmi.client.*;
// import com.ibm.websphere.pmi.stat.*;
// import com.ibm.websphere.management.exception.*;

// import javax.management.*;
// import java.util.*;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMIDSVar {
  String jdbcDriver;
  String datasourceName;

  // DataSource
  long dsFreePoolSize = 0;
  long dsCurPoolSize = 0;
  long dsPoolSizeMax = 0;
  long dsWaitThread = 0;
  // extensive
  long dsAllocateCount = 0;
  long dsReturnCount = 0;
  long dsCreateCount = 0;
  long dsPrepStmtCacheCount = 0;
  // more extensive
  long dsCloseCount = 0;
  long dsFaultCount = 0;
  double dsPercentUsed = 0.0;
  double dsUseTime = 0.0;
  double dsWaitTime = 0.0;
  double dsJDBCTime = 0.0;

  TRWSPMIDSVar () {
    resetData();
  }

  public void resetData() {
    // DataSource
    dsFreePoolSize = 0;
    dsCurPoolSize = 0;
    dsPoolSizeMax = 0;
    dsWaitThread = 0;
    // extensive
    dsAllocateCount = 0;
    dsReturnCount = 0;
    dsCreateCount = 0;
    dsPrepStmtCacheCount = 0;
    // more extensive
    dsCloseCount = 0;
    dsFaultCount = 0;
    dsPercentUsed = 0.0;
    dsUseTime = 0.0;
    dsWaitTime = 0.0;
    dsJDBCTime = 0.0;
  }

  public void setJDBCDriverName(String val) {
    jdbcDriver = val;
  }

  public String getJDBCDriverName() {
    return jdbcDriver;
  }

  public void setDataSourceName(String val) {
    datasourceName = val;
  }

  public String getDataSourceName() {
    return datasourceName;
  }

  // JDBCDriver DataSource
  public void setDSFreePoolSize(long val) {
    dsFreePoolSize = val;
  }

  public void setDSCurPoolSize(long val) {
    dsCurPoolSize = val;
  }

  public void setDSPoolSizeMax(long val) {
    dsPoolSizeMax = val;
  }

  public void setDSWaitThread(long val) {
    dsWaitThread = val;
  }

  public void setDSAllocateCount(long val) {
    dsAllocateCount = val;
  }

  public void setDSReturnCount(long val) {
    dsReturnCount = val;
  }

  public void setDSCreateCount(long val) {
    dsCreateCount = val;
  }

  public void setDSPrepStmtCacheDiscardCount(long val) {
    dsPrepStmtCacheCount = val;
  }

  public void setDSCloseCount(long val) {
    dsCloseCount = val;
  }

  public void setDSFaultCount(long val) {
    dsFaultCount = val;
  }

  public void setDSPercentUsed(double val) {
    dsPercentUsed = val;
  }

  public void setDSUseTime(double val) {
    dsUseTime = val;
  }

  public void setDSWaitTime(double val) {
    dsWaitTime = val;
  }

  public void setDSJDBCTime(double val) {
    dsJDBCTime = val;
  }

  public long getDSFreePoolSize() {
    return dsFreePoolSize;
  }

  public long getDSCurPoolSize() {
    return dsCurPoolSize;
  }

  public long getDSPoolSizeMax() {
    return dsPoolSizeMax;
  }

  public long getDSWaitThread() {
    return dsWaitThread;
  }

  public long getDSAllocateCount() {
    return dsAllocateCount;
  }

  public long getDSReturnCount() {
    return dsReturnCount;
  }

  public long getDSCreateCount() {
    return dsCreateCount;
  }

  public long getDSPrepStmtCacheDiscardCount() {
    return dsPrepStmtCacheCount; 
  }

  public long getDSCloseCount() {
    return dsCloseCount;
  }

  public long getDSFaultCount() {
    return dsFaultCount;
  }

  public double getDSPercentUsed() {
    return dsPercentUsed;
  }

  public double getDSUseTime() {
    return dsUseTime;
  }

  public double getDSWaitTime() {
    return dsWaitTime;
  }

  public double getDSJDBCTime() {
    return dsJDBCTime;
  }
}



