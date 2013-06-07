package com.toprunner.websphere.pmi;

import java.util.*;
// import java.lang.*;
// import java.io.*;

import com.toprunner.websphere.pmi.TRWSPMIVar;
import com.toprunner.websphere.pmi.TRKeyValue;
import com.toprunner.websphere.pmi.TRWSPMIUtils;
// import com.toprunner.mail.SendMail;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMIDis {
  static TRWSPMIDis instance = null;

  // monitor level
  boolean monitorEX = false;
  boolean monitorMO = false;

  TRWSPMIUtils ref = null;

  public TRWSPMIDis(boolean monitorEX, boolean monitorMO) {
    this.monitorEX = monitorEX;
    this.monitorMO = monitorMO; 
    ref = TRWSPMIUtils.getInstance();
  }

  public static TRWSPMIDis getInstance(boolean monitorEX, boolean monitorMO) {
    if (instance == null)
      instance = new TRWSPMIDis (monitorEX, monitorMO);

    return instance;
  }

  public String printStatisticsHeaderData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;

    if (nodeVct == null)
      return null;

    // get node name
    sb.append("\n");
    sb.append(String.format("%-25s", ref.getDate() + " " + ref.getTime()));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
      sb.append(String.format("%25.24s", wspmivar.getNodeName()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", " "));

    // get server name
    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
      sb.append(String.format("%25.24s", wspmivar.getServerName()));
    }

    return sb.toString();
  }

  public String printStatisticsDefaultData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ Default thread pool
    sb.append("\n\n");
    sb.append("Default Thread Pool\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getDFThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getDFCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getDFCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsHAManagerThreadPoolData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ HAManager Thread Pool
    sb.append("\n\n");
    sb.append("HAManager Thread Pool\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getHAMThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getHAMCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getHAMCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsMessageListenerData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ MessageListener
    sb.append("\n\n");
    sb.append("Message Listener Thread\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getMLThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getMLCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getMLCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsORBThreadPoolData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ ORB Thread Pool
    sb.append("\n\n");
    sb.append("ORB Thread Pool\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getORBThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getORBCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getORBCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsSCData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ SOAP thread pool
    sb.append("\n\n");
    sb.append("SOAP Thread Pool\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getSCThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getSCCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getSCCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsDCSData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ TCPChannel.DCS Thread pool
    sb.append("\n\n");
    sb.append("TCPChannel Thread Pool\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getDCSThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getDCSCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getDCSCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsProcessDiscoveryData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ Process Discovery
    sb.append("\n\n");
    sb.append("Process Discovery Thread\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getPDThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getPDCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getPDCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsWebContainerData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ Web Container
    sb.append("\n\n");
    sb.append("WebContainer\n");
    sb.append(String.format("%25s", "Max Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0) {
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
        ref.emailMsg("Server: " + wspmivar.getNodeName(), 0);
      }
      else
        sb.append(String.format("%25s", wspmivar.getWCThreadMaxThreshold()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getWCCurrentThreadPool()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Active Threads: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getWCCurrentThreadActive()));
    }

    return sb.toString();
  }

  public String printStatisticsJVMData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ JVM
    sb.append("\n\n");
    sb.append("JVM\n");
    sb.append(String.format("%25s", "Max Heap Size: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getJVMHeapMax() <= 0){
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      }
      else
        sb.append(String.format("%25s", wspmivar.getJVMHeapMax()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Cur Allocated Heap Size: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getJVMHeapMax() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getJVMCurHeapAlocated()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Used Heap Size: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getJVMHeapMax() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getJVMUsedMem()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Free Heap Size: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getJVMHeapMax() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getJVMFreeMem()));
    }

    return sb.toString();
  }

  public String printStatisticsDataSourceData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    TRWSPMIDSVar wspmidsvar = null;
    Vector <TRWSPMIDSVar> vct = null;
    int i;
    int k;

    if (nodeVct == null)
      return null;

    // ================ DataSource
    sb.append("\n\n");
    sb.append("DataSource\n");
    sb.append(String.format("%25s", " "));

    // get the first one in the list.  All others are the same
    wspmivar = (TRWSPMIVar)nodeVct.elementAt(0);
    vct = wspmivar.getDS();

    if (vct != null) {
      for (k = 0; k < vct.size(); k++) {
        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getDS();
          wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);
          sb.append(String.format("%25.24s", wspmidsvar.getDataSourceName()));
        }

        sb.append("\n");
        sb.append(String.format("%25s", "Max Conn Pool Size: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getDS();
          wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

          if (wspmidsvar.getDSPoolSizeMax() <= 0)
            sb.append(String.format("%25s", TRKeyValue.NA));
          else
            sb.append(String.format("%25s", wspmidsvar.getDSPoolSizeMax()));
        }

        sb.append("\n");
        sb.append(String.format("%25s", "Cur Allocated Pool Size: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getDS();
          wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

          if (wspmidsvar.getDSPoolSizeMax() <= 0)
            sb.append(String.format("%25s", TRKeyValue.NA));
          else
            sb.append(String.format("%25s", wspmidsvar.getDSCurPoolSize()));
        }

        sb.append("\n");
        sb.append(String.format("%25s", "Curr Free Pool Conn: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getDS();
          wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

          if (wspmidsvar.getDSPoolSizeMax() <= 0)
            sb.append(String.format("%25s", TRKeyValue.NA));
          else
            sb.append(String.format("%25s", wspmidsvar.getDSFreePoolSize()));
        }

        sb.append("\n");
        sb.append(String.format("%25s", "Curr Waiting Threads: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getDS();
          wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

          if (wspmidsvar.getDSPoolSizeMax() <= 0)
            sb.append(String.format("%25s", TRKeyValue.NA));
          else
            sb.append(String.format("%25s", wspmidsvar.getDSWaitThread()));
        }

        if (monitorMO | monitorEX) {
          // DataSource extension parameters
          sb.append("\n");
          sb.append(String.format("%25s", "JDBC Time (ms): "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25.5f", wspmidsvar.getDSJDBCTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Use Time (ms): "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25.5f", wspmidsvar.getDSUseTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Wait Time (ms): "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25.5f", wspmidsvar.getDSWaitTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Percent Used: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSPercentUsed()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Prep Stmt Discard Count: "));
  
          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);
  
            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSPrepStmtCacheDiscardCount()));
          }
        }

        if (monitorEX) {
          // DataSource extension parameters
          sb.append("\n");
          sb.append(String.format("%25s", "Curr Return Count: "));
  
          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);
  
            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSReturnCount()));
          }
  
          sb.append("\n");
          sb.append(String.format("%25s", "Curr Allocate Count: "));
  
          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);
  
            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSAllocateCount()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Curr Create Count: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);
  
            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSCreateCount()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Close Count: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSCloseCount()));
          }
  
          sb.append("\n");
          sb.append(String.format("%25s", "Fault Count: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getDS();
            wspmidsvar = (TRWSPMIDSVar)vct.elementAt(k);

            if (wspmidsvar.getDSPoolSizeMax() <= 0)
              sb.append(String.format("%25s", TRKeyValue.NA));
            else
              sb.append(String.format("%25s", wspmidsvar.getDSFaultCount()));
          }
        }

        // print this if there is more than one datasource
        if (k < vct.size() - 1){
          sb.append("\n\n");
          sb.append(String.format("%25s", " "));
        }
      }
    }

    return sb.toString();
  }

  public String printStatisticsSessionData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    TRWSPMISSVar wspmissvar = null;
    Vector <TRWSPMISSVar> vct = null;
    int i;

    if (nodeVct == null)
      return null;

    // ================ Session
    sb.append("\n\n");
    sb.append("Sessions\n");

    // get the first one in the list.  All others are the same
    wspmivar = (TRWSPMIVar)nodeVct.elementAt(0);
    vct = wspmivar.getSession();

    if (vct != null) {
      for (int k = 0; k < vct.size(); k++) {
        // need only a name from a first one only
        wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
        sb.append(wspmissvar.getAppName());

        sb.append("\n");
        sb.append(String.format("%25s", "Live Count: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getSession();
          wspmissvar = (TRWSPMISSVar)vct.elementAt(k);

          if (wspmivar.getWCThreadMaxThreshold() <= 0) {
            sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
          }
          else
            sb.append(String.format("%25s", wspmissvar.getSessionLiveCount()));
        }

        if (monitorMO | monitorEX) {
          sb.append("\n");
          sb.append(String.format("%25s", "Live Time: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25.5f", wspmissvar.getSessionLifeTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "No Room For New Session: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25s", wspmissvar.getSessionNoRoomForNewSession()));
          }
        }

        if (monitorEX) {
          sb.append("\n");
          sb.append(String.format("%25s", "External Read Time: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25.5f", wspmissvar.getSessionExtReadTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "External Write Time: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25.5f", wspmissvar.getSessionExtWriteTime()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "External Write Size: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25s", wspmissvar.getSessionExtWriteSize()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "External Read Size: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getSession();
            wspmissvar = (TRWSPMISSVar)vct.elementAt(k);
  
            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25s", wspmissvar.getSessionExtReadSize()));
          }
        }

        // print this if there is more than one datasource
        if (k < vct.size() - 1){
          sb.append("\n\n");
        }
      }
    }

    return sb.toString();
  }

  public String printStatisticsSVLJSPData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    TRWSPMISVar wspmisvar = null;
    Vector <TRWSPMISVar> vct = null;
    int i;

    if (nodeVct == null)
      return null;

    // ================ Servlet JSP
    sb.append("\n\n");
    sb.append("Servlet/JSP/HTML\n");

    // get the first one in the list.  All others are the same
    wspmivar = (TRWSPMIVar)nodeVct.elementAt(0);
    vct = wspmivar.getServletJsp();

    if (vct != null) {
      for (int k = 0; k < vct.size(); k++) {
        // need only a name from a first one only
        wspmisvar = (TRWSPMISVar)vct.elementAt(k);
        sb.append(wspmisvar.getAppName() + " - " + wspmisvar.getServletOrJsp());

        sb.append("\n");
        sb.append(String.format("%25s", "Request Count: "));

        for (i = 0; i < nodeVct.size(); i++) {
          wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
          vct = wspmivar.getServletJsp();
          wspmisvar = (TRWSPMISVar)vct.elementAt(k);

          if (wspmivar.getWCThreadMaxThreshold() <= 0)
            sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
          else
            sb.append(String.format("%25s", wspmisvar.getRequestCount()));
        }

        if (monitorMO | monitorEX) {
          sb.append("\n");
          sb.append(String.format("%25s", "Service Time: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getServletJsp();
            wspmisvar = (TRWSPMISVar)vct.elementAt(k);

            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25.5f", wspmisvar.getServiceTime()));
          }
        }

        if (monitorEX) {
          sb.append("\n");
          sb.append(String.format("%25s", "Concurrent Request: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getServletJsp();
            wspmisvar = (TRWSPMISVar)vct.elementAt(k);

            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25s", wspmisvar.getConcurrentRequest()));
          }

          sb.append("\n");
          sb.append(String.format("%25s", "Error Count: "));

          for (i = 0; i < nodeVct.size(); i++) {
            wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);
            vct = wspmivar.getServletJsp();
            wspmisvar = (TRWSPMISVar)vct.elementAt(k);

            if (wspmivar.getWCThreadMaxThreshold() <= 0)
              sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
            else
              sb.append(String.format("%25s", wspmisvar.getErrorCount()));
          }
        }

        // print this if there is more than one datasource
        if (k < vct.size() - 1) {
          sb.append("\n\n");
        }
      }
    }

    return sb.toString();
  }

  public String printStatisticsWorkloadManagementClientData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ Workload Management Client
    sb.append("\n\n");
    sb.append("Workload Management Client\n");
    sb.append(String.format("%25s", "OutgoingIIOPRequestCount:"));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0){
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      }
      else
        sb.append(String.format("%25s", wspmivar.getWLMCIIOPRequestCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "ClientClusterUpdateCount:"));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getWLMCClientClusterUpdateCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "ClientResponseTime:"));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25.5f", wspmivar.getWLMCClientResponseTime()));
    }

    return sb.toString();
  }

  public String printStatisticsHAManagerData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();

    return sb.toString();
  }

  public String printStatisticsTransactionManagerData(Vector <TRWSPMIVar> nodeVct) {
    StringBuffer sb = new StringBuffer();
    // String s = null;
    TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;

    if (nodeVct == null)
      return null;

    // ================ Transaction Manager
    sb.append("\n\n");
    sb.append("Transaction Manager\n");
    sb.append(String.format("%25s", "Global Begun Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0){
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      }
      else
        sb.append(String.format("%25s", wspmivar.getTMGlobalBegunCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Local Begun Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMtmLocalBegunCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Active Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMActiveCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Committed Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMCommittedCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Rolledback Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMRolledbackCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Global Timeout Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMGlobalTimeoutCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Local Timeout Count: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25s", wspmivar.getTMLocalTimeoutCount()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Global Tran Time: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25.5f", wspmivar.getTMGlobalTranTime()));
    }

    sb.append("\n");
    sb.append(String.format("%25s", "Local Tran Time: "));

    for (int i = 0; i < nodeVct.size(); i++) {
      wspmivar = (TRWSPMIVar)nodeVct.elementAt(i);

      if (wspmivar.getWCThreadMaxThreshold() <= 0)
        sb.append(String.format("%25s", TRKeyValue.DOWN_ERROR));
      else
        sb.append(String.format("%25.5f", wspmivar.getTMLocalTranTime()));
    }

    return sb.toString();
  }
}

