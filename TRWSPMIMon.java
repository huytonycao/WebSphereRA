package com.toprunner.websphere.pmi;

import com.ibm.websphere.management.AdminClient;
// import com.ibm.websphere.management.AdminClientFactory;

// import com.ibm.websphere.pmi.*;
// import com.ibm.websphere.pmi.client.*;
import com.ibm.websphere.pmi.stat.*;
// import com.ibm.websphere.management.exception.*;

import javax.management.*;
import java.util.*;
// import java.lang.*;
// import java.io.*;
// import java.util.regex.Pattern;

import com.toprunner.websphere.pmi.TRWSPMIVar;
import com.toprunner.websphere.pmi.TRKeyValue;
import com.toprunner.websphere.pmi.TRWSPMIUtils;
// import com.toprunner.mail.SendMail;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMIMon {
  static TRWSPMIMon instance = null;

  TRWSPMIUtils ref = null;

  public TRWSPMIMon() {
    ref = TRWSPMIUtils.getInstance();
  }

  public static TRWSPMIMon getInstance() {
    if (instance == null)
      instance = new TRWSPMIMon ();

    return instance;
  }

  public void monitorDefaultThreadPool(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorDefaultThreadPool";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor defaultPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "Default"});

    if (defaultPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{defaultPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic defaultStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setDFCurrentThreadPool(defaultStatistic.getCurrent());

           // fix the negative number
           defaultStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (defaultStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + defaultStatistic.getCurrent() + " changed to: 0");
             wspmivar.setDFCurrentThreadActive(0);
           }
           else {
             wspmivar.setDFCurrentThreadActive(defaultStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic defaultStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setDFThreadMaxThreshold(defaultStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF defaultPoolSD");
  }

  public void monitorProcessDiscovery(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorProcessDiscovery";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor pdPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "ProcessDiscovery"});

    if (pdPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{pdPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic pdStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setPDCurrentThreadPool(pdStatistic.getCurrent());

           // fix the negative number
           pdStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (pdStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + pdStatistic.getCurrent() + " changed to: 0");
             wspmivar.setPDCurrentThreadActive(0);
           }
           else {
             wspmivar.setPDCurrentThreadActive(pdStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic pdStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setPDThreadMaxThreshold(pdStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF perfOName, params, signature");
     }
//     else
 //      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDF pdPoolSD");
  }

  public void monitorMessageListener(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorMessageListener";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor mlPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "MessageListenerThreadPool"});

    if (mlPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{mlPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic mlStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setMLCurrentThreadPool(mlStatistic.getCurrent());

           // fix the negative number
           mlStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (mlStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + mlStatistic.getCurrent() + " changed to: 0");
             wspmivar.setMLCurrentThreadActive(0);
           }
           else {
             wspmivar.setMLCurrentThreadActive(mlStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic mlStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setMLThreadMaxThreshold(mlStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorML wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorML perfOName, params, signature");
     }
//     else
//      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorML mlPoolSD");
  }

  public void monitorORBThreadPool(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorORBThreadPool";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor orbPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "ORB.thread.pool"});

    if (orbPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{orbPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic orbStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setORBCurrentThreadPool(orbStatistic.getCurrent());

           // fix the negative number
           orbStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (orbStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + orbStatistic.getCurrent() + " changed to: 0");
             wspmivar.setORBCurrentThreadActive(0);
           }
           else {
             wspmivar.setORBCurrentThreadActive(orbStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic orbStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setORBThreadMaxThreshold(orbStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorORB wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorORB perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorORB orbPoolSD");
  }

  public void monitorSCThreadPool(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorORBThreadPool";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor soapPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "SoapConnectorThreadPool"});

    if (soapPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{soapPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic soapStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setSCCurrentThreadPool(soapStatistic.getCurrent());

           // fix the negative number
           soapStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (soapStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + soapStatistic.getCurrent() + " changed to: 0");
             wspmivar.setSCCurrentThreadActive(0);
           }
           else {
             wspmivar.setSCCurrentThreadActive(soapStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic soapStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setSCThreadMaxThreshold(soapStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSC wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSC perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSC soapPoolSD");
  }

  public void monitorDCSThreadPool(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorORBThreadPool";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor dcsPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "TCPChannel.DCS"});

    if (dcsPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{dcsPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic dcsStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setDCSCurrentThreadPool(dcsStatistic.getCurrent());

           // fix the negative number
           dcsStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (dcsStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + dcsStatistic.getCurrent() + " changed to: 0");
             wspmivar.setDCSCurrentThreadActive(0);
           }
           else {
             wspmivar.setDCSCurrentThreadActive(dcsStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic dcsStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setDCSThreadMaxThreshold(dcsStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDCS wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDCS perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDCS dcsPoolSD");
  }

  public void monitorHAManagerThreadPool(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorDefaultThreadPool";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor hamPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "HAManager.thread.pool"});

    if (hamPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{hamPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic hamStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setHAMCurrentThreadPool(hamStatistic.getCurrent());

           // fix the negative number
           hamStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (hamStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + hamStatistic.getCurrent() + " changed to: 0");
             wspmivar.setHAMCurrentThreadActive(0);
           }
           else {
             wspmivar.setDFCurrentThreadActive(hamStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic hamStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setHAMThreadMaxThreshold(hamStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorHAM wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorHAM perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorHAM hamPoolSD");
  }

  public void monitorWebContainer(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorWebContainer";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    // WebContainer thread pool
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor webContainerPoolSD = new StatDescriptor(new String[] {WSThreadPoolStats.NAME, "WebContainer"});

    if (webContainerPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{webContainerPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic webconStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setWCCurrentThreadPool(webconStatistic.getCurrent());

           // fix the negative number
           webconStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (webconStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + webconStatistic.getCurrent() + " changed to: 0");
             wspmivar.setWCCurrentThreadActive(0);
           }
           else {
             wspmivar.setWCCurrentThreadActive(webconStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic webconStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setWCThreadMaxThreshold(webconStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC webContainerPoolSD");
  }

  public void monitorJVM(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName, ObjectName jvmOName) throws Exception {
    final String fn = "monitorJVM";
    Object[] params = null;
    String[] signature = null;

    signature = new String[] {"javax.management.ObjectName","java.lang.Boolean"};
    params = new Object[] {jvmOName, new Boolean(false)};

    if (perfOName != null && params != null && signature != null) {
      WSStats jvmStats = (WSStats) ac.invoke(perfOName, "getStatsObject", params, signature);

      if (jvmStats != null) {
        WSRangeStatistic jvmHeapStatistic = (WSRangeStatistic) jvmStats.getStatistic (WSJVMStats.HeapSize);
        wspmivar.setJVMCurHeapAlocated(jvmHeapStatistic.getCurrent());
        WSBoundaryStatistic jvmHeapStatisticWS = (WSBoundaryStatistic)jvmStats.getStatistic (WSJVMStats.HeapSize);
        wspmivar.setJVMHeapMax(jvmHeapStatisticWS.getUpperBound());
        WSCountStatistic jvmHeapStatistic2 = (WSCountStatistic) jvmStats.getStatistic (WSJVMStats.UsedMemory);
        wspmivar.setJVMUsedMem(jvmHeapStatistic2.getCount());
        jvmHeapStatistic2 = (WSCountStatistic) jvmStats.getStatistic (WSJVMStats.FreeMemory);
        wspmivar.setJVMFreeMem(jvmHeapStatistic2.getCount());
      }
      else
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorJVM jvmStats");
    }
    else
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorJVM perfOName, params, signature");
  }

  public void monitorDataSource(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorDataSource";
    Vector<TRWSPMIDSVar> vct = wspmivar.getDS();
    String jdbcName = null;
    String prevjdbcName = null;
    String datasourceName = null;
    Object[] params = null;
    String[] signature = null;
    StatDescriptor jdbcStatistic = null;
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    int i;
    int k;

     if (vct != null) {
       for (k = 0; k < vct.size(); k++) {
         TRWSPMIDSVar dsVar = (TRWSPMIDSVar)vct.elementAt(k);
         datasourceName = dsVar.getDataSourceName();
         jdbcName = dsVar.getJDBCDriverName();

         // JDBCDriver
         if (jdbcName != null && datasourceName != null) {
           if (prevjdbcName == null || (prevjdbcName != null && prevjdbcName.equals(jdbcName))) {
             // don't invoke this method for the same jdbc driver to save time
             signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
             jdbcStatistic = new StatDescriptor(new String[] {WSJDBCConnectionPoolStats.NAME, jdbcName});
             params = new Object[]{new StatDescriptor[]{jdbcStatistic}, new Boolean(true)};
           }

           if (perfOName != null && params != null && signature != null) {
             if (prevjdbcName == null || (prevjdbcName != null && prevjdbcName.equals(jdbcName))) {
               // don't invoke this method for the same jdbc driver to save time
               wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);
               prevjdbcName = jdbcName;
             }

             if (wsStats != null && wsStats[0] != null) {
               // sub stat
               WSStats [] subStats = wsStats[0].getSubStats();
               WSRangeStatistic dsWSRangeStats = null;
               WSBoundaryStatistic dsWSBoundaryStats = null;
               WSCountStatistic dsWSCountStats = null;
               WSAverageStatistic dsWSAvgStats = null;

               if (dsVar != null && subStats != null) {
                 for (i = 0; i < subStats.length; i++) {
                   if (subStats[i] != null && (subStats[i].getName()).equals(datasourceName)) {
                     // get the max pool size setup
                     dsWSBoundaryStats = (WSBoundaryStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.FreePoolSize);
                     dsVar.setDSPoolSizeMax(dsWSBoundaryStats.getUpperBound());

                     dsWSRangeStats = (WSRangeStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.FreePoolSize);
                     dsVar.setDSFreePoolSize(dsWSRangeStats.getCurrent());
                     dsWSRangeStats = (WSRangeStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.PoolSize);
                     dsVar.setDSCurPoolSize(dsWSRangeStats.getCurrent());
                     dsWSRangeStats = (WSRangeStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.PercentUsed);
                     dsVar.setDSPercentUsed(dsWSRangeStats.getCurrent());

                     // correct waiting thread if nesscessary
                     dsWSRangeStats = (WSRangeStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.WaitingThreadCount);

                     if (dsWSRangeStats.getCurrent() > 0) {
                       if (dsVar.getDSCurPoolSize() == dsVar.getDSPoolSizeMax() && dsVar.getDSFreePoolSize() <= 0) {
                         dsVar.setDSWaitThread(dsWSRangeStats.getCurrent());
                     }
                     else {
                       ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported waiting threads: " + dsWSRangeStats.getCurrent() + " changed to: 0");
                       dsVar.setDSWaitThread(0);
                     }
                   }
                   else
                     dsVar.setDSWaitThread(dsWSRangeStats.getCurrent());
                     // correct waiting thread end

                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.AllocateCount);
                     dsVar.setDSAllocateCount(dsWSCountStats.getCount());
                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.FreedCount);
                     dsVar.setDSReturnCount(dsWSCountStats.getCount());
                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.CreateCount);
                     dsVar.setDSCreateCount(dsWSCountStats.getCount());
                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.PrepStmtCacheDiscardCount);
                     dsVar.setDSPrepStmtCacheDiscardCount(dsWSCountStats.getCount());
                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.CloseCount);
                     dsVar.setDSCloseCount(dsWSCountStats.getCount());
                     dsWSCountStats = (WSCountStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.FaultCount);
                     dsVar.setDSFaultCount(dsWSCountStats.getCount());

                     dsWSAvgStats = (WSAverageStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.UseTime);
                     dsVar.setDSUseTime(dsWSAvgStats.getMean());
                     dsWSAvgStats = (WSAverageStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.JDBCTime);
                    dsVar.setDSJDBCTime(dsWSAvgStats.getMean());
                    dsWSAvgStats = (WSAverageStatistic)subStats[i].getStatistic(WSJDBCConnectionPoolStats.WaitTime);
                    dsVar.setDSWaitTime(dsWSAvgStats.getMean());
                  }
                }
              }
              else
                ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDS dsVar subStats");
            }
          }
          else
            ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDS wsStats");
        }
        else
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorDS jdbcName datasourceName");
      }
    }
  }

  public void monitorServlet(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorServlet";
    WSStats sjStats = null;
    WSAverageStatistic sjAvgStats = null;
    WSCountStatistic sjCountStats = null;
    WSRangeStatistic sjRangeStats = null;
    String appName;
    ObjectName sjOName;
    int k;
    Object[] params = null;
    String[] signature = null;

    Vector<TRWSPMISVar> vct = wspmivar.getServletJsp();

    if (vct != null) {
      for (k = 0; k < vct.size(); k++) {
        TRWSPMISVar sjVar = (TRWSPMISVar)vct.elementAt(k);

        if (sjVar != null) {
          appName = sjVar.getAppName();
          sjOName = sjVar.getSJOName();

          if (sjOName != null) {
            try {
              // Sessions
              signature = new String[] {"javax.management.ObjectName","java.lang.Boolean"};
              params = new Object[] {sjOName, new Boolean(false)};

              if (perfOName != null && signature != null && params != null) {
                sjStats = (WSStats) ac.invoke(perfOName, "getStatsObject", params, signature);

                //if (appName != null && sjStats != null && appName.equals(sjStats.getAppName())) {
                if (appName != null && sjStats != null) {
                  sjAvgStats = (WSAverageStatistic)sjStats.getStatistic(WSWebAppStats.ServletStats.ServiceTime);
                  sjVar.setServiceTime(sjAvgStats.getMean());

                  sjRangeStats = (WSRangeStatistic)sjStats.getStatistic(WSWebAppStats.ServletStats.ConcurrentRequests);
                  sjVar.setConcurrentRequest(sjRangeStats.getCurrent());

                  sjCountStats = (WSCountStatistic)sjStats.getStatistic(WSWebAppStats.ServletStats.RequestCount);
                  sjVar.setRequestCount(sjCountStats.getCount());
                  sjCountStats = (WSCountStatistic)sjStats.getStatistic(WSWebAppStats.ServletStats.ErrorCount);
                  sjVar.setErrorCount(sjCountStats.getCount());
                }
              }
              else
                 ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Null parameters monitorSS perfOName signature params");
            }
            catch (Exception ss) {
              ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Session error");
              ref.printStackTrace(fn, TRKeyValue.LOGERROR, ss);
            }
          }
        }
        else
          ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Null parameters monitorSVL sjVar");
      }
    }
    else
      ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Null parameters monitorSVL vct");
  }

  public void monitorSession (AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorSession";
    WSStats sessionStats = null;
    WSAverageStatistic ssAvgStats = null;
    WSCountStatistic ssCountStats = null;
    WSRangeStatistic ssRangeStats = null;
    String appName;
    ObjectName ssOName;
    int k;
    Object[] params = null;
    String[] signature = null;

    Vector<TRWSPMISSVar> vct = wspmivar.getSession();

    if (vct != null) {
      for (k = 0; k < vct.size(); k++) {
        TRWSPMISSVar ssVar = (TRWSPMISSVar)vct.elementAt(k);

        if (ssVar != null) {
          appName = ssVar.getAppName();
          ssOName = ssVar.getSessionOName();

          if (ssOName != null) {
            try {
              // Sessions
              signature = new String[] {"javax.management.ObjectName","java.lang.Boolean"};
              params = new Object[] {ssOName, new Boolean(false)};

              if (perfOName != null && signature != null && params != null) {
                sessionStats = (WSStats) ac.invoke(perfOName, "getStatsObject", params, signature);

                if (appName != null && sessionStats != null && appName.equals(sessionStats.getName())) {
                  ssAvgStats = (WSAverageStatistic)sessionStats.getStatistic(WSSessionManagementStats.LifeTime);
                  ssVar.setSessionLifeTime(ssAvgStats.getMean());
                  ssAvgStats = (WSAverageStatistic)sessionStats.getStatistic(WSSessionManagementStats.ExternalReadTime);
                  ssVar.setSessionExtReadTime(ssAvgStats.getMean());
                  ssAvgStats = (WSAverageStatistic)sessionStats.getStatistic(WSSessionManagementStats.ExternalWriteTime);
                  ssVar.setSessionExtWriteTime(ssAvgStats.getMean());
                  ssAvgStats = (WSAverageStatistic)sessionStats.getStatistic(WSSessionManagementStats.ExternalWriteSize);
                  ssVar.setSessionExtWriteSize(ssAvgStats.getMean());
                  ssAvgStats = (WSAverageStatistic)sessionStats.getStatistic(WSSessionManagementStats.ExternalReadSize);
                  ssVar.setSessionExtReadSize(ssAvgStats.getMean());

                  ssRangeStats = (WSRangeStatistic)sessionStats.getStatistic(WSSessionManagementStats.LiveCount);
                  ssVar.setSessionLiveCount(ssRangeStats.getCurrent());

                  ssCountStats = (WSCountStatistic)sessionStats.getStatistic(WSSessionManagementStats.NoRoomForNewSessionCount);
                  ssVar.setSessionNoRoomForNewSession(ssCountStats.getCount());
                }
              }
              else
                ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSS perfOName signature params");
            }
            catch (Exception ss) {
              ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Session error");
              ref.printStackTrace(fn, TRKeyValue.LOGERROR, ss);
          }
        }
      }
      else
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSS ssVar");
      }
    }
    else
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorSS vct");
  }

  public void monitorTransactionManager(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName, ObjectName tmOName) throws Exception {
    final String fn = "monitorTransactionManager";
    WSStats wsStats = null;
    Object[] params = null;
    String[] signature = null;
    WSCountStatistic tmCountStats = null;
    WSAverageStatistic tmAvgStats = null;

    signature = new String[] {"javax.management.ObjectName","java.lang.Boolean"};
    params = new Object[] {tmOName, new Boolean(false)};

    if (perfOName != null && params != null && signature != null) {
      wsStats = (WSStats)ac.invoke(perfOName, "getStatsObject", params, signature);

      if (wsStats != null) {
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.GlobalBegunCount);
        wspmivar.setTMGlobalBegunCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.LocalBegunCount);
        wspmivar.setTMtmLocalBegunCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.ActiveCount);
        wspmivar.setTMActiveCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.CommittedCount);
        wspmivar.setTMCommittedCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.RolledbackCount);
        wspmivar.setTMRolledbackCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.GlobalTimeoutCount);
        wspmivar.setTMGlobalTimeoutCount(tmCountStats.getCount());
        tmCountStats = (WSCountStatistic)wsStats.getStatistic(WSJTAStats.LocalTimeoutCount);
        wspmivar.setTMLocalTimeoutCount(tmCountStats.getCount());

        tmAvgStats = (WSAverageStatistic)wsStats.getStatistic(WSJTAStats.GlobalTranTime);
        wspmivar.setTMGlobalTranTime(tmAvgStats.getMean());
        tmAvgStats = (WSAverageStatistic)wsStats.getStatistic(WSJTAStats.LocalTranTime);
        wspmivar.setTMLocalTranTime(tmAvgStats.getMean());
      }
    }
    else
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorTM perfOName, params, signature");
  }

  public void monitorHAManager(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorWorkloadManagement";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    WSCountStatistic wlmCountStats = null;
    WSAverageStatistic wlmAvgStats = null;

    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor wlmPoolSD = new StatDescriptor(new String[] {WSWLMStats.ClientStats.NAME, "wlmModule.client"});

    if (wlmPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{wlmPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
          wlmCountStats = (WSCountStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.OutgoingIIOPRequestCount);
          wspmivar.setWLMCIIOPRequestCount(wlmCountStats.getCount());
          wlmCountStats = (WSCountStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.ClientClusterUpdateCount);
          wspmivar.setWLMCClientClusterUpdateCount(wlmCountStats.getCount());

          wlmAvgStats = (WSAverageStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.ClientResponseTime);
          wspmivar.setWLMCClientResponseTime(wlmAvgStats.getMean());
        }
        else
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC wsStats");
      }
      else
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC wlmPoolSD");
  }

  public void monitorWLMClient(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName, ObjectName wlmcOName) throws Exception {
    final String fn = "monitorWorkloadManagement";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    WSCountStatistic wlmCountStats = null;
    WSAverageStatistic wlmAvgStats = null;

    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor wlmPoolSD = new StatDescriptor(new String[] {WSWLMStats.ClientStats.NAME, "wlmModule.client"});

    if (wlmPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{wlmPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
          wlmCountStats = (WSCountStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.OutgoingIIOPRequestCount);
          wspmivar.setWLMCIIOPRequestCount(wlmCountStats.getCount());
          wlmCountStats = (WSCountStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.ClientClusterUpdateCount);
          wspmivar.setWLMCClientClusterUpdateCount(wlmCountStats.getCount());

          wlmAvgStats = (WSAverageStatistic)wsStats[0].getStatistic(WSWLMStats.ClientStats.ClientResponseTime);
          wspmivar.setWLMCClientResponseTime(wlmAvgStats.getMean());
        }
        else
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC wsStats");
      }
      else
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC perfOName, params, signature");
     }
//     else
//       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWLMC wlmPoolSD");
  }

/*
  public void monitorWLMServer(AdminClient ac, TRWSPMIVar wspmivar, ObjectName perfOName) throws Exception {
    final String fn = "monitorWorkloadManagement";
    com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
    Object[] params = null;
    String[] signature = null;
    signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
    StatDescriptor wlmPoolSD = new StatDescriptor(new String[] {WSWLMStats.NAME, "wlmModule.server"});

    if (wlmPoolSD != null) {
      params = new Object[]{new StatDescriptor[]{wlmPoolSD}, new Boolean(true)};

      if (perfOName != null && params != null && signature != null) {
        wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);

        if (wsStats != null && wsStats[0] != null) {
           WSRangeStatistic webconStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setWCCurrentThreadPool(webconStatistic.getCurrent());

           // fix the negative number
           webconStatistic = (WSRangeStatistic) wsStats[0].getStatistic (WSThreadPoolStats.ActiveCount);

           if (webconStatistic.getCurrent() < 0) {
             ref.printLogMsg(fn, TRKeyValue.LOGWARN, "IBM reported Current Thread Active: " + webconStatistic.getCurrent() + " changed to: 0");
             wspmivar.setWCCurrentThreadActive(0);
           }
           else {
             wspmivar.setWCCurrentThreadActive(webconStatistic.getCurrent());
           }
           // end fix the negative number

           WSBoundaryStatistic webconStatisticWS = (WSBoundaryStatistic)wsStats[0].getStatistic (WSThreadPoolStats.PoolSize);
           wspmivar.setWCThreadMaxThreshold(webconStatisticWS.getUpperBound());
         }
         else
           ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC wsStats");
       }
       else
         ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC perfOName, params, signature");
     }
     else
       ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters monitorWC webContainerPoolSD");
  }
*/
}

