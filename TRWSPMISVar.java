package com.toprunner.websphere.pmi;

import javax.management.*;
// import java.util.*;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMISVar {
  ObjectName sjOName;
  String appname;
  String servletorjsp;

  // servlet jsp
  long requestCount = 0;
  long concurrentRequest = 0;
  double serviceTime = 0;
  long errorCount = 0;

  TRWSPMISVar () {
  }

  public void resetData() {
    requestCount = 0;
    concurrentRequest = 0;
    serviceTime = 0.0;
    errorCount = 0;
  }

  public void setSJOName(ObjectName val) {
    sjOName = val;
  }

  public void setAppName(String val) {
    appname = val;
  }

  public void setServletOrJsp(String val) {
    servletorjsp = val;
  }

  public ObjectName getSJOName() {
    return sjOName;
  }

  public String getAppName() {
    return appname;
  }

  public String getServletOrJsp() {
    return servletorjsp;
  }

  // ====================
  public void setRequestCount(long val) {
    requestCount = val;
  }

  public void setConcurrentRequest(long val) {
    concurrentRequest = val;
  }

  public void setServiceTime(double val) {
    serviceTime = val;
  }

  public void setErrorCount(long val) {
    errorCount = val;
  }

  public long getRequestCount() {
    return requestCount;
  }

  public long getConcurrentRequest() {
    return concurrentRequest;
  }

  public double getServiceTime() {
    return serviceTime;
  }

  public long getErrorCount() {
    return errorCount;
  }
}



