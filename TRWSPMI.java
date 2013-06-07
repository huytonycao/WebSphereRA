package com.toprunner.websphere.pmi;

import com.ibm.websphere.management.AdminClient;
import com.ibm.websphere.management.AdminClientFactory;

import com.ibm.websphere.pmi.*;
// import com.ibm.websphere.pmi.client.*;
import com.ibm.websphere.pmi.stat.*;
// import com.ibm.websphere.management.exception.*;

import javax.management.*;
import java.util.*;
// import java.lang.*;
import java.io.*;
// import java.util.regex.Pattern;

import com.toprunner.websphere.pmi.TRWSPMIVar;
import com.toprunner.websphere.pmi.TRKeyValue;
import com.toprunner.websphere.pmi.TRWSPMIUtils;
import com.toprunner.websphere.pmi.TRWSPMIDis;
import com.toprunner.websphere.pmi.TRWSPMIMon;
//import com.toprunner.mail.SendMail;

/*
 * @author tony cao
 * @version 1.0.1
 */

public class TRWSPMI implements PmiConstants {
  String host = null;
  String port = null;
  String connType = null;
  AdminClient ac = null;

  ObjectName sessionOName = null;

  boolean monitorJVM = false;

  // monitor thread pool
  boolean monitorWC = false;
  boolean monitorDF = false;
  boolean monitorHAM = false;
  boolean monitorML = false;
  boolean monitorORB = false;
  boolean monitorPD = false;
  boolean monitorSC = false;
  boolean monitorDCS = false;
  boolean monitorTM = false;
  boolean monitorHAMgr = false;
  boolean monitorWLMC = false;
  boolean monitorWLMS = false;

  boolean monitorDS = false;
  boolean monitorSS = false;
  boolean monitorSVL = false;

  // monitor level
  boolean monitorEX = false;
  boolean monitorMO = false;

  boolean reset = false;

  // in ra thoi
  boolean inrathoi = false;

  int monitorDuration = 60;
  int monitorInterval = 15;
  PrintWriter out = null;
  PrintWriter err = null;

  int OBJECTNAMENOTFOUND = -1;
  int UNABLEGETOBJECTNAME = 0;
  int OBJECTNAMEFOUND = 1;

  Vector <TRWSPMIVar> nodeVct = null;

  static TRWSPMIUtils ref = null;
  static TRWSPMIDis pref = null;
  static TRWSPMIMon mref = null;

  public static void main (String[] args) {
    String fn = "main";
    TRWSPMI instance = new TRWSPMI();

    // test vars
    String nodes = null;
    String servers = null;
    String datasources = null;
    String sjApplications = null;
    String ssApplications = null;
    String monitorstr = TRKeyValue.turnOn;
    BufferedReader in = null;

    // mail stuff
    String mailhoststr = null;
    String mailuseridstr = null;
    String mailpasswdstr = null;
    String mailtostr = null;
    String mailfromstr = null;
    String mailccstr = null;
    String mailbccstr = null;

    // security stuff
    String SecurityUserName = null;
    String SecurityPasswd = null;
    String TrustStoreFile = null;
    String KeyStoreFile = null;
    String TrustStorePasswd = null;
    String KeyStorePassword = null;
    boolean security = false;

    // files
    String outfile = null;
    String errfile = null;

    // screen
    boolean display = false;

    if (args.length < 4) {
      instance.usage();
      System.exit(1);
    }

    Vector <String> displayorder = new Vector<String> ();

    try {
      FileReader fstream = new FileReader(args[4]);
      in = new BufferedReader(fstream);

      String line = null;
      String key = null;

      while ((line = in.readLine()) != null) {
        if (line.equals("\n") || line.length() <= 0)
          continue;

        line = line.trim();

        if (line.charAt(0) == '#') 
          continue;

        StringTokenizer stk = new StringTokenizer(line, "=");
        String check = null;

        while (stk.hasMoreTokens()) {
          key = stk.nextToken(); 

          if (key != null && key.equalsIgnoreCase(TRKeyValue.nodesList)) {
            if (stk.hasMoreTokens())
              nodes = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.appServersList)) {
            if (stk.hasMoreTokens())
              servers = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.datasourceList)) {
            if (stk.hasMoreTokens())
              datasources = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.sjAppList)) {
            if (stk.hasMoreTokens())
              sjApplications = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.ssAppList)) {
            if (stk.hasMoreTokens())
              ssApplications = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.outFileNameStr)) {
            if (stk.hasMoreTokens())
              outfile = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.errFileNameStr)) {
            if (stk.hasMoreTokens())
              errfile = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorI)) {
            if (stk.hasMoreTokens())
              instance.monitorInterval = Integer.parseInt(stk.nextToken());

              if (instance.monitorInterval < 5)
                instance.monitorInterval = 5;
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorD)) {
            if (stk.hasMoreTokens())
              instance.monitorDuration = Integer.parseInt(stk.nextToken());
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorSSStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorSS = true;
            }

            displayorder.add(TRKeyValue.monitorSSStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorSJStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorSVL = true;
            }

            displayorder.add(TRKeyValue.monitorSJStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorL)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.high)) {
                instance.monitorMO = true;
                instance.monitorEX = true;
              }
              else if (check != null && check.equalsIgnoreCase(TRKeyValue.medium))
                instance.monitorMO = true;
            }
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorJVMStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorJVM = true;
            }

            displayorder.add(TRKeyValue.monitorJVMStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorDFStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorDF = true;
            }

            displayorder.add(TRKeyValue.monitorDFStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorHAMStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorHAM = true;
            }

            displayorder.add(TRKeyValue.monitorHAMStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorMLStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorML = true;
            }

            displayorder.add(TRKeyValue.monitorMLStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorORBStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorORB = true;
            }

            displayorder.add(TRKeyValue.monitorORBStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorPDStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorPD = true;
            }

            displayorder.add(TRKeyValue.monitorPDStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorSCStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorSC = true;
            }

            displayorder.add(TRKeyValue.monitorSCStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorDCSStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorDCS = true;
            }

            displayorder.add(TRKeyValue.monitorDCSStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorWCStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorWC = true;
            }

            displayorder.add(TRKeyValue.monitorWCStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorDSStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorDS = true;
            }

            displayorder.add(TRKeyValue.monitorDSStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorTMStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorTM = true;
            }

            displayorder.add(TRKeyValue.monitorTMStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorHAMgrStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorHAMgr = true;
            }

            displayorder.add(TRKeyValue.monitorWLMCStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorWLMCStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorWLMC = true;
            }

            displayorder.add(TRKeyValue.monitorWLMCStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.monitorWLMSStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.monitorWLMS = true;
            }

            displayorder.add(TRKeyValue.monitorWLMSStr);
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.displaystr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && !check.equalsIgnoreCase(TRKeyValue.turnOn)) {
                // don't use printOutMsg function.
                System.out.println("Console display turn off.");
                display = false;
              }
              else 
                display = true;
            }
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.inRaThoiStr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn))
                instance.inrathoi = true;
            }
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.resetvaluesstr)) {
            if (stk.hasMoreTokens()) {
              check = stk.nextToken();

              if (check != null && check.equalsIgnoreCase(TRKeyValue.turnOn)) 
                instance.reset = true;
            }
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailhost)) {
            if (stk.hasMoreTokens())
              mailhoststr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailuserid)) {
            if (stk.hasMoreTokens())
              mailuseridstr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailpasswd)) {
            if (stk.hasMoreTokens())
              mailpasswdstr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailto)) {
            if (stk.hasMoreTokens())
              mailtostr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailfrom)) {
            if (stk.hasMoreTokens())
              mailfromstr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailcc)) {
            if (stk.hasMoreTokens())
              mailccstr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.mailbcc)) {
            if (stk.hasMoreTokens())
              mailbccstr = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.SecurityEnableStr)) {
            String securitystr = null;

            if (stk.hasMoreTokens()) {
              securitystr = stk.nextToken();

              if (securitystr != null && securitystr.equalsIgnoreCase(TRKeyValue.turnOn))
                security = true;
            }
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.SecurityUserNameStr)) {
            if (stk.hasMoreTokens())
              SecurityUserName = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.SecurityPasswdStr)) {
            if (stk.hasMoreTokens())
              SecurityPasswd = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.TrustStoreFileStr)) {
            if (stk.hasMoreTokens())
              TrustStoreFile = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.KeyStoreFileStr)) {
            if (stk.hasMoreTokens())
              KeyStoreFile = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.TrustStorePasswdStr)) {
            if (stk.hasMoreTokens())
              TrustStorePasswd = stk.nextToken();
          }
          else if (key != null && key.equalsIgnoreCase(TRKeyValue.KeyStorePasswordStr)) {
            if (stk.hasMoreTokens())
              KeyStorePassword = stk.nextToken();
          }
          else if (key != null) {
            System.out.println("Name value pair not found for: [ " + key + " ] in line ==> " + line);

            //skip the value if no key found
            if (stk.hasMoreTokens())
              stk.nextToken();
          }
        }
      }

      in.close();
    } 
    catch (Exception e) {
      try {
        if (in != null)
          in.close();
      } 
      catch (Exception ef) {
        System.err.println("Main");
        ef.printStackTrace();
      }

      System.err.println("File input error");
      System.err.println("Main 1");
      e.printStackTrace();
    }

    instance.monitorDuration = (instance.monitorDuration * 60 ) / instance.monitorInterval;
    instance.monitorInterval = instance.monitorInterval * 1000;

    instance.setHost(args[0]);
    instance.setPort(args[1]);
    instance.setConnType(args[2]);
    monitorstr = args[3];

    if (outfile == null || errfile == null) 
      return;

    ref = TRWSPMIUtils.getInstance(outfile, errfile, display, mailhoststr, mailuseridstr,
                                   mailpasswdstr, mailtostr, mailfromstr, mailccstr, mailbccstr);
   
    ref.printOutMsg("\n" + TRKeyValue.seperator);
    ref.printOutMsg(TRKeyValue.version);
    ref.printOutMsg(TRKeyValue.writtenBy);
    ref.printOutMsg(TRKeyValue.writtenDate);
    ref.printOutMsg(TRKeyValue.authorEmail);
    ref.printOutMsg(TRKeyValue.bugs);
    ref.printOutMsg(TRKeyValue.seperator + "\n");

    if (nodes != null && !nodes.equals("") && servers != null && !servers.equals("")) {
      instance.createNodeServerList(nodes, servers, datasources, ssApplications, sjApplications);
      instance.ac = instance.getAdminClient(security, SecurityUserName, SecurityPasswd, TrustStoreFile, 
                                            KeyStoreFile, TrustStorePasswd, KeyStorePassword);
    }
    else {
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Nodes or Servers could not be empty.  Or check your configuration for mistyped.");
      instance.cleanUp();
      return;
    }

    if (!instance.checkNodes(nodes)) {
      instance.cleanUp();
      return;
    }

    if (instance.getObjectNames() == instance.OBJECTNAMENOTFOUND) {
      instance.cleanUp();
      return;
    }

    if (monitorstr.equalsIgnoreCase(TRKeyValue.turnOn)) 
      instance.getStatisticsData(displayorder);

    instance.cleanUp();
  }

  public TRWSPMI() {
    nodeVct = new Vector <TRWSPMIVar> ();
  }

  public void cleanUp() {
    register(false);
    ref.cleanUp();
  }

  public void usage() {
    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------------------------");
    System.out.println("Usage: java TRWSPMI <host> <port> <1|0> <on|off> <config_file");
    System.out.println("       host - Network Deployment or a Base hostname");
    System.out.println("       port - Network Deployment or a Base port");
    System.out.println("       1 - Set monitor using SOAP, 0 - not yet define");
    System.out.println("       on - Set monitor on, off - Set monitor off");
    System.out.println("       config_file - Config file");
    System.out.println("-----------------------------------------------------------------------------------" );
    System.out.println("\n");
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public void setConnType(String conn) {
    this.connType = conn;
  }

  public boolean checkNodeExists(String nodeName) {
    final String fn = "checkNodeExists";
    String query;
    ObjectName queryName;
    // ObjectName nodeAgent;
    Set<?> s;

    if (nodeName == null)
      return false;

    query = "WebSphere:type=NodeAgent,node=" + nodeName + ",*";

    try {
      queryName = new ObjectName(query);
      s = ac.queryNames(queryName, null);

      if (!s.isEmpty()) {
        return true;
      }
    }
    catch (Exception e) {
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Node error");
      ref.printStackTrace(fn, TRKeyValue.LOGERROR, e);
    }

    return false;
  }

  public boolean isMonitorTurnOn () {
    if (monitorSS || monitorJVM || monitorDS || monitorEX || monitorMO || monitorSVL ||
        monitorWC || monitorDF || monitorHAM || monitorML || monitorORB || monitorPD ||
        monitorSC || monitorDCS || monitorTM || monitorWLMC || monitorWLMS || monitorHAMgr)
      return true;

    return false;
  }

  public boolean checkNodes(String nodeslist) {
    final String fn = "checkNodes";
    StringTokenizer nodes = new StringTokenizer(nodeslist, TRKeyValue.PIPE);
    String nodeName;
    // String query;
    // ObjectName queryName;
    // ObjectName nodeAgent;
    // Set s;

    // go through each node and set server name and other params
    while (nodes.hasMoreTokens()) {
      nodeName = nodes.nextToken();

      if (!checkNodeExists(nodeName)) {
        System.out.println("Node was not found or not running: " + nodeName);
        ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Node was not found or not running: " + nodeName);
        return false;
      }
      else 
        ref.printLogMsg(fn, TRKeyValue.LOGINFO, "Found Node:" + nodeName);
        System.out.println("Found Node: " + nodeName);
    }

    return true;
  }

  public void createNodeServerList(String nodeslist, String serverslist, String datasourceStr, 
                                   String sessionAppName, String servletjspAppName) {
    final String fn = "createNodeServerList";
    StringTokenizer servers = new StringTokenizer(serverslist, TRKeyValue.PIPE);
    TRWSPMIVar wspmivar = null;
    String server = null;
    String node = null;
    String datasource = null;
    String datasources = null;
    String jdbcdriver = null;
    String warAppName = null;
    String ssAppName = null;
    String sjAppName = null;
    String sjAppNames = null;
    String warName = null;

    // get server for each node
    while (servers.hasMoreTokens()) {
      StringTokenizer nodes = new StringTokenizer(nodeslist, TRKeyValue.PIPE);
      server = servers.nextToken();

      // go through each node and set server name and other params 
      while (nodes.hasMoreTokens()) {
        node = nodes.nextToken();
        wspmivar = new TRWSPMIVar();
        wspmivar.setNodeName(node);
        wspmivar.setServerName(server); 
        wspmivar.setMonitorWebContainer(monitorWC); 
        wspmivar.setMonitorHAManagerThreadPool(monitorHAM); 
        wspmivar.setMonitorProcessDiscovery(monitorPD); 
        wspmivar.setMonitorSOAPConnector(monitorSC); 
        wspmivar.setMonitorTCPChannelDCS(monitorDCS); 
        wspmivar.setMonitorMessageListener(monitorML); 
        wspmivar.setMonitorORB(monitorORB); 
        wspmivar.setMonitorDefault(monitorDF); 
        wspmivar.setMonitorJVM(monitorJVM); 
        wspmivar.setMonitorTransactionManager(monitorTM); 
        wspmivar.setMonitorWLMC(monitorWLMC); 
        wspmivar.setMonitorWLMS(monitorWLMS); 
        wspmivar.setMonitorDataSource(monitorDS); 
        wspmivar.setMonitorHAManager(monitorHAMgr); 

        // set datasource and jdbc name for each server
        if (datasourceStr != null) {
          // get jdbc:datasource|datasource
          StringTokenizer jdbc = new StringTokenizer(datasourceStr, TRKeyValue.SEMICOLON);

          while (jdbc.hasMoreTokens()) {
            jdbcdriver = jdbc.nextToken();

            StringTokenizer jdbcds = new StringTokenizer(jdbcdriver, TRKeyValue.COLON);
          
            // get jdbc driver name
            if (jdbcds.hasMoreTokens())
              jdbcdriver = jdbcds.nextToken();

            // get datasource name
            while (jdbcds.hasMoreTokens()) {
              datasources = jdbcds.nextToken();

              StringTokenizer ds = new StringTokenizer(datasources, TRKeyValue.PIPE);
          
              while (ds.hasMoreTokens()) {
                datasource = ds.nextToken();
                TRWSPMIDSVar dsvar = new TRWSPMIDSVar();
                dsvar.setJDBCDriverName(jdbcdriver);
                dsvar.setDataSourceName(datasource);
                wspmivar.setDS(dsvar);
              }
            }
          }
        }

        // set seesion name for each server
        if (sessionAppName != null) {
          StringTokenizer ss = new StringTokenizer(sessionAppName, TRKeyValue.PIPE);

          while (ss.hasMoreTokens()) {
            ssAppName = ss.nextToken();
            TRWSPMISSVar ssvar = new TRWSPMISSVar();
            ssvar.setAppName(ssAppName);

            if (!wspmivar.setSession(ssvar))
              ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Duplicate App name: " + ssAppName);
          }
        }

        // set servlet/jsp name for each server
        if (servletjspAppName != null) {
          // get appname:servlet|jsp|...
          StringTokenizer warnames = new StringTokenizer(servletjspAppName, TRKeyValue.SEMICOLON);

          while (warnames.hasMoreTokens()) {
            warAppName = warnames.nextToken();

            StringTokenizer warnamestr = new StringTokenizer(warAppName, TRKeyValue.COLON);

            // get war name
            if (warnamestr.hasMoreTokens())
              warName = warnamestr.nextToken();

            // get datasource name
            while (warnamestr.hasMoreTokens()) {
              sjAppNames = warnamestr.nextToken();

              StringTokenizer sj = new StringTokenizer(sjAppNames, TRKeyValue.PIPE);

              while (sj.hasMoreTokens()) {
                sjAppName = sj.nextToken();
                TRWSPMISVar sjvar = new TRWSPMISVar();
                sjvar.setAppName(warName);
                sjvar.setServletOrJsp(sjAppName);

                if (!wspmivar.setServletJsp(sjvar))
                  ref.printLogMsg(fn, TRKeyValue.LOGWARN, "Duplicate Servlet/JSP/HTML name: " + sjAppName);
              }
            }
          }
        }

        nodeVct.add(wspmivar);
      }
    }
  }

  public AdminClient getAdminClient(boolean security, String username, String passwd, 
                                    String trustfile, String keyfile, String trustpasswd, String keypasswd) {
    final String fn = "getAdminClient";
    AdminClient ac = null;

    java.util.Properties props = new java.util.Properties();

    props.put(AdminClient.CONNECTOR_HOST, host);
    props.put(AdminClient.CONNECTOR_PORT, port);

    if (connType != null && connType.equals("1"))
      props.put(AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP);
    else {
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Invalide connection type");
      System.exit(1);
    }

    if (security) {
      if (username != null && !username.equals("") && passwd != null && !passwd.equals("") && 
          keyfile != null && !keyfile.equals("") && keypasswd != null && !keypasswd.equals("") &&
          trustfile != null && !trustfile.equals("") && trustpasswd != null && !trustpasswd.equals("")) {
        props.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, "true");
        props.setProperty(AdminClient.USERNAME, username);
        props.setProperty(AdminClient.PASSWORD, passwd);
        props.setProperty(AdminClient.CACHE_DISABLED, "false");
        props.setProperty("javax.net.ssl.keyStore", keyfile);
        props.setProperty("javax.net.ssl.keyStorePassword", keypasswd);
        props.setProperty("javax.net.ssl.trustStore", trustfile);
        props.setProperty("javax.net.ssl.trustStorePassword", trustpasswd);
      }
      else {
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Please check username, passwd, keyfile, keypasswd, trustfile and trustpasswd");
      }
    }

    try {
      ac = AdminClientFactory.createAdminClient(props);
    }
    catch (Exception ex) {
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Unable to create AdminClient");
      ref.printStackTrace(fn, TRKeyValue.LOGERROR, ex);
    }

    return ac;
  }

  public String backSlashChar(String str) {
    if (str == null)
      return null;

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '.') {
        sb.append("\\");
        sb.append(".");
      }
      else if (str.charAt(i) == '#') {
        sb.append("\\");
        sb.append("#");
      }
      else 
        sb.append(str.charAt(i));
    }

    return sb.toString();
  }

/*
  public boolean isStringMatch(String str, String match) {
    int i;
    int k = 0;
    int count = 0;

    if (str != null && match != null) {
      for (i = 0; i < str.length(); i++) {
        if (k < match.length() && str.charAt(i) == match.charAt(k)) {
          k++;
          count++;

          if (count == match.length() - 1) {
            return true;
          }
        }
        else {
          k = 0;
          count = 0;
        }
      }
    }

    return false;
  }
*/

  public int getObjectNames() {
    final String fn = "getObjectNames";

    try {
      javax.management.ObjectName on = new javax.management.ObjectName("WebSphere:*");
      Set <?> objectNameSet = ac.queryNames(on, null);
      int j;
      int k;
      TRWSPMIVar wspmivar;
      TRWSPMISSVar wspmissvar;
      TRWSPMISVar wspmisvar;
      Vector <TRWSPMISVar> vct;
      Vector <TRWSPMISSVar> vctss;
      String nodeName;
      String serverName;
      String appName;
      String sjName;

      if (objectNameSet != null) {
        Iterator <?> i = objectNameSet.iterator();

        while (i.hasNext()) {
          on = (ObjectName)i.next();
          String type = on.getKeyProperty("type");
          String node = on.getKeyProperty("node");
          String process = on.getKeyProperty("process");

          if (type != null && (type.equals("Perf") || type.equals("JVM") || type.equals("SessionManager") ||
                               type.equals("Servlet") || type.equals("JSP") || type.equals("HAManager.thread.pool") ||
                               type.equals("Default") || type.equals("MessageListenerThreadPool") || 
                               type.equals("ORB.thread.pool") || type.equals("ProcessDiscovery") ||
                               type.equals("SoapConnectorThreadPool") || type.equals("TCPChannel.DCS") ||
                               type.equals("Transaction") 
                               //|| type.equals("wlmModule#wlmModule.client")
             )) {
            for (j = 0; j < nodeVct.size(); j++) {
              wspmivar = nodeVct.elementAt(j);
              nodeName = wspmivar.getNodeName(); 
              serverName = wspmivar.getServerName(); 

              if (node != null && node.equals(nodeName) && process.equals(serverName)) {
                if (inrathoi) {  
                  System.out.println(on.toString());
                }

                if (wspmivar != null) {
                  if (type != null && type.equals("Perf")) {
                    wspmivar.setPerfOName(on);
                  }
                  else if (type != null && type.equals("JVM")) {
                    wspmivar.setJVMOName(on);
                  }
                  else if (type != null && type.equals("Transaction")) {
                    wspmivar.setTMName(on);
                  }
                  else if (type != null && type.equals("SessionManager")) {
                    vctss = wspmivar.getSession();

                    if (vctss != null) {
                      for (k = 0; k < vctss.size(); k++) {
                        wspmissvar = (TRWSPMISSVar)vctss.elementAt(k);

                        if (wspmissvar != null) {
                          appName = wspmissvar.getAppName();

                          if (appName != null) {
                            if (ref.isStringMatch(on.toString(), appName)) {
                              wspmissvar.setSessionOName(on);
                              //sessionOName = on;
                              k = vctss.size();
                            }
                          }
                        }
                      }
                    }
                  }
                  else if (type != null && (type.equals("Servlet") || type.equals("JSP"))) {
                    String name = on.getKeyProperty("name");
                    vct = wspmivar.getServletJsp();

                    if (vct != null) {
                      for (k = 0; k < vct.size(); k++) {
                        wspmisvar = (TRWSPMISVar)vct.elementAt(k);

                        if (wspmisvar != null) {
                          appName = wspmisvar.getAppName();
                          sjName = wspmisvar.getServletOrJsp();

                          if (appName != null && sjName != null && name != null) {
                            if (ref.isStringMatch(on.toString(), appName) && name.equals(sjName)) {
                              wspmisvar.setSJOName(on);
                              k = vct.size();
                            }
                          }
                        }
                      }
                    }
                  }
                }
//                  else if (type != null && type.equals("WLMAppServer")) {
//                  else if (type != null && type.equals("wlmModule#wlmModule.client")) {
//                    wspmivar.setWLMCName(on);
//System.out.println(on.toString());
//                  }

                j = nodeVct.size();
              }
            }
          }
        }
      }
      else {
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "No object name found");
        return OBJECTNAMENOTFOUND;
      }
    }
    catch (Exception ex) {
      ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Unable to get object name");
      ref.printStackTrace(fn, TRKeyValue.LOGERROR, ex);
      return UNABLEGETOBJECTNAME;
    }

    return OBJECTNAMEFOUND;
  }

  //
  // tat ca dua vao WebContainer de xem len hoac suong.  Co the doi qua JVM
  //
  public void printStatisticsData(Vector<String> displayorder) {
    StringBuffer sb = new StringBuffer();
    StringBuffer finalsb = new StringBuffer();
    String s = null;
    // TRWSPMIVar wspmivar = null;
    // TRWSPMIDSVar wspmidsvar = null;
    // Vector vct = null;
    String namestr;

    if (displayorder == null) 
      return;

    if (nodeVct == null)
      return;

    pref = TRWSPMIDis.getInstance(monitorEX, monitorMO);

    for (int i = 0; i < displayorder.size(); i++) {
      namestr = (String)displayorder.elementAt(i);

      if (namestr != null) {
        if (monitorDF && namestr.equalsIgnoreCase(TRKeyValue.monitorDFStr)) {
          s = pref.printStatisticsDefaultData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorHAM && namestr.equalsIgnoreCase(TRKeyValue.monitorHAMStr)) {
          s = pref.printStatisticsHAManagerThreadPoolData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorML && namestr.equalsIgnoreCase(TRKeyValue.monitorMLStr)) {
          s = pref.printStatisticsMessageListenerData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorPD && namestr.equalsIgnoreCase(TRKeyValue.monitorPDStr)) {
          s = pref.printStatisticsProcessDiscoveryData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorORB && namestr.equalsIgnoreCase(TRKeyValue.monitorORBStr)) {
          s = pref.printStatisticsORBThreadPoolData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorSC && namestr.equalsIgnoreCase(TRKeyValue.monitorSCStr)) {
          s = pref.printStatisticsSCData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorORB && namestr.equalsIgnoreCase(TRKeyValue.monitorDCSStr)) {
          s = pref.printStatisticsDCSData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorWC && namestr.equalsIgnoreCase(TRKeyValue.monitorWCStr)) {
          s = pref.printStatisticsWebContainerData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorJVM && namestr.equalsIgnoreCase(TRKeyValue.monitorJVMStr)) {
          s = pref.printStatisticsJVMData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorDS && namestr.equalsIgnoreCase(TRKeyValue.monitorDSStr)) {
          s = pref.printStatisticsDataSourceData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorSS && namestr.equalsIgnoreCase(TRKeyValue.monitorSSStr)) {
          s = pref.printStatisticsSessionData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorSVL && namestr.equalsIgnoreCase(TRKeyValue.monitorSJStr)) {
          s = pref.printStatisticsSVLJSPData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorHAMgr && namestr.equalsIgnoreCase(TRKeyValue.monitorHAMgrStr)) {
          s = pref.printStatisticsHAManagerData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorTM && namestr.equalsIgnoreCase(TRKeyValue.monitorTMStr)) {
          s = pref.printStatisticsTransactionManagerData(nodeVct);

          if (s != null)
            sb.append(s);
        }

        if (monitorWLMC && namestr.equalsIgnoreCase(TRKeyValue.monitorWLMCStr)) {
          s = pref.printStatisticsWorkloadManagementClientData(nodeVct);

          if (s != null)
            sb.append(s);
        }
      }
    }

    // go last to get better time
    if (isMonitorTurnOn()) 
      s = pref.printStatisticsHeaderData(nodeVct);

    if (s != null)
      finalsb.append(s);

    if (sb != null)
      finalsb.append(sb.toString());

    ref.printOutMsg(finalsb.toString());
  }

  public void register(boolean reg) {
    final String fn = "register";
    int i = 0;
    ObjectName perfOName = null;
    TRWSPMIVar wspmivar = null;
    String nodeName = null;
    String serverName = null;
    Object[] params;
    String[] signature;
    // String v5PropFlag = System.setProperty("websphereV5Statistics", "false");

    for (i = 0; i < nodeVct.size(); i++) {
      signature = new String[]{"java.lang.String"};

      wspmivar = nodeVct.elementAt(i);
      perfOName = wspmivar.getPerfOName();
      nodeName = wspmivar.getNodeName(); 
      serverName = wspmivar.getServerName(); 

      //STATISTIC_SET_EXTENDED or STATISTIC_SET_ALL or STATISTIC_SET_NONE 
      if (reg) {
        if (monitorEX)
          params = new Object[] {StatConstants.STATISTIC_SET_ALL};
        else // if (monitorMO)
          params = new Object[] {StatConstants.STATISTIC_SET_EXTENDED}; 
        // else
          // params = new Object[] {StatConstants.STATISTIC_SET_BASIC}; 
      }
      else {
        System.out.println("Unsetting PMI monitor on node: " + nodeName);
        params = new Object[] {StatConstants.STATISTIC_SET_NONE}; 
      }

      try {
        if (perfOName != null && params != null && signature != null) {
          ac.invoke(perfOName, "setStatisticSet", params, signature);

          if (reg) {
            ref.printLogMsg(fn, TRKeyValue.LOGINFO, "register : [" + i + "] " + nodeName + " - " + serverName);
          }
          else {
            ref.printLogMsg(fn, TRKeyValue.LOGINFO, "unregister : [" + i + "] " + nodeName + " - " + serverName);
          }
        }
        else
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters");

      }
      catch (Exception ex) {
        if (wspmivar != null)
          wspmivar.setServerRunning(false);

        if (reg) {
          ref.emailMsg("Unable to monitor node: " + nodeName + " - " + serverName, 0);
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Unable to monitor node: [" + i + "]" + nodeName + " - " + serverName);
          ref.printOutMsg("Unable to monitor node: [" + i + "]" + nodeName + " - " + serverName);
        }
        else {
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Unable to unmonitor node: [" + i + "]" + nodeName + " - " + serverName);
          ref.printOutMsg("Unable to unmonitor node: [" + i + "]" + nodeName + " - " + serverName);
        }

        ref.printStackTrace(fn, TRKeyValue.LOGERROR, ex);
      }
    }
  }

  public void getStatisticsData(Vector<String> displayorder) {
    final String fn = "getStatisticsData";
    // int i = 0;
    int j = 0;
    // int k = 0;
    ObjectName perfOName = null;
    ObjectName jvmOName = null;
    ObjectName tmOName = null;
    ObjectName wlmcOName = null;
    TRWSPMIVar wspmivar = null;
    String nodeName = null;
    String serverName = null;
    // String jdbcName = null;
    long startTime = 0;
    long endTime = 0;
    long timeRemainder = 0;

    // String v5PropFlag = System.setProperty("websphereV5Statistics", "false");

    // set values to zero before start
    if (reset)
      register(false);

    // start monitor 
    register(true);

    mref = TRWSPMIMon.getInstance();

    for (int timetorun = 0; timetorun < monitorDuration; timetorun++) {
      startTime = System.currentTimeMillis();

      for (j = 0; j < nodeVct.size(); j++) {
        try {
          wspmivar = nodeVct.elementAt(j);

          if (wspmivar != null && (!wspmivar.isServerRunning() || wspmivar.getPerfOName() == null)) {
            if (getObjectNames() == OBJECTNAMEFOUND) {
              register(true);
              wspmivar.setServerRunning(true);
            }
          }
            
          if (wspmivar != null) {
            perfOName = wspmivar.getPerfOName();
            nodeName = wspmivar.getNodeName(); 
            serverName = wspmivar.getServerName(); 
            jvmOName = wspmivar.getJVMOName();
            tmOName = wspmivar.getTMOName();
//            wlmcOName = wspmivar.getWLMCOName();
          }

          if (wspmivar != null && wspmivar.isServerRunning()) {
            if (inrathoi) {  
              // array everything de coi
              com.ibm.websphere.pmi.stat.WSStats[] wsStats = null;
              Object[] params = null;
              String[] signature = null;
              signature = new String[]{"[Lcom.ibm.websphere.pmi.stat.StatDescriptor;","java.lang.Boolean"};
              params = new Object[]{new StatDescriptor[]{new StatDescriptor(null)}, new Boolean(true)};
  
              if (perfOName != null && params != null && signature != null)
                wsStats = (com.ibm.websphere.pmi.stat.WSStats[])ac.invoke(perfOName, "getStatsArray", params, signature);
              else
                ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Null parameters 1");

              // print all stats
              System.out.println(wsStats[0].toString());
              
              return;
            }

            // tat ca dua vao WC sau nay se doi qua JVM.  Nho doi luon tat ca printStatistic....
            if (monitorWC | monitorSS | monitorSVL | monitorDF | monitorHAM | monitorML | 
                monitorDS | monitorORB | monitorSC | monitorDCS | monitorPD | monitorORB |
                monitorTM | monitorWLMC | monitorWLMS | monitorHAMgr) 
              mref.monitorWebContainer(ac, wspmivar, perfOName);
  
            if (monitorDF) 
              mref.monitorDefaultThreadPool(ac, wspmivar, perfOName);
  
            if (monitorHAM) 
              mref.monitorHAManagerThreadPool(ac, wspmivar, perfOName);
  
            if (monitorML) 
              mref.monitorMessageListener(ac, wspmivar, perfOName);
  
            if (monitorORB) 
              mref.monitorORBThreadPool(ac, wspmivar, perfOName);
  
            if (monitorSC) 
              mref.monitorSCThreadPool(ac, wspmivar, perfOName);
  
            if (monitorDCS) 
              mref.monitorDCSThreadPool(ac, wspmivar, perfOName);
  
            if (monitorPD) 
              mref.monitorProcessDiscovery(ac, wspmivar, perfOName);
  
            if (monitorORB) 
              mref.monitorProcessDiscovery(ac, wspmivar, perfOName);
  
            if (monitorJVM) 
              mref.monitorJVM(ac, wspmivar, perfOName, jvmOName);
  
            if (monitorDS) 
              mref.monitorDataSource(ac, wspmivar, perfOName);
 
            if (monitorSS) 
              mref.monitorSession(ac, wspmivar, perfOName);

            if (monitorSVL)
              mref.monitorServlet(ac, wspmivar, perfOName);

            if (monitorTM)
              mref.monitorTransactionManager(ac, wspmivar, perfOName, tmOName);

            if (monitorHAMgr)
              mref.monitorHAManager(ac, wspmivar, perfOName);

            if (monitorWLMC)
              mref.monitorWLMClient(ac, wspmivar, perfOName, wlmcOName);
          }
        }
        catch (Exception ex1) {
          ref.emailMsg("Unable to get data on node: " + nodeName + " - " + serverName, 0);
          ref.printLogMsg(fn, TRKeyValue.LOGERROR, "Unable to get data on node: [" + j + "] " + nodeName + " - " + serverName);
          ref.printOutMsg("Unable to get data on node: [" + j + "] " + nodeName + " - " + serverName);
          ref.printStackTrace(fn, TRKeyValue.LOGERROR, ex1);

          if (wspmivar != null) {
            wspmivar.setServerRunning(false);
            wspmivar.resetData();
          }
        }
      }
 
      printStatisticsData(displayorder);

      try {
        endTime = System.currentTimeMillis();
        timeRemainder = monitorInterval - (endTime - startTime);

        if (timeRemainder < 1)
          timeRemainder = 0;

        endTime = 0;
        startTime = 0;

        ref.emailMsg(TRKeyValue.endPerReq, 1);

        java.lang.Thread.sleep(timeRemainder);
      } 
      catch (Exception et) {
        ref.printLogMsg(fn, TRKeyValue.LOGERROR, "All");
        ref.printStackTrace(fn, TRKeyValue.LOGERROR, et);
      }
    }

    ref.printOutMsg("==========> Monitor time expired. Monitor completed. <==========");
  }
}

