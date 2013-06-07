package com.toprunner.websphere.pmi;

import javax.management.*;
// import java.util.*;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMISSVar {
  ObjectName ssOName = null;
  String appname;

  // Session
  double lifeTime = 0;
  long liveCount = 0;
  long noRoomForNewSessinonCount = 0;
  double extReadTime = 0.0;
  double extWriteTime = 0.0;
  double extReadSize = 0.0;
  double extWriteSize = 0.0;

  TRWSPMISSVar () {
    resetData();
  }

  public void resetData() {
    // session
    lifeTime = 0.0;
    liveCount = 0;
    noRoomForNewSessinonCount = 0;
    extReadTime = 0.0;
    extWriteTime = 0.0;
    extReadSize = 0.0;
    extWriteSize = 0.0;
  }

  public void setSessionOName(ObjectName val) {
    ssOName = val;
  }

  public ObjectName getSessionOName() {
    return ssOName;
  }

  public void setAppName(String val) {
    appname = val;
  }

  public String getAppName() {
    return appname;
  }

  // Session
  public void setSessionLifeTime(double val) {
    lifeTime = val;
  }

  public void setSessionLiveCount(long val) {
    liveCount = val;
  }

  public void setSessionNoRoomForNewSession(long val) {
    noRoomForNewSessinonCount = val;
  }

  public void setSessionExtReadTime(double val) {
    extReadTime = val;
  }

  public void setSessionExtWriteTime(double val) {
    extWriteTime = val;
  }

  public void setSessionExtReadSize(double val) {
    extReadSize = val;
  }

  public void setSessionExtWriteSize(double val) {
    extWriteSize = val;
  }

  public double getSessionLifeTime() {
    return lifeTime;
  }

  public long getSessionLiveCount() {
    return liveCount;
  }

  public long getSessionNoRoomForNewSession() {
    return noRoomForNewSessinonCount;
  }

  public double getSessionExtReadTime() {
    return extReadTime;
  }

  public double getSessionExtWriteTime() {
    return extWriteTime;
  }

  public double getSessionExtReadSize() {
    return extReadSize;
  }

  public double getSessionExtWriteSize() {
    return extWriteSize;
  }
}



