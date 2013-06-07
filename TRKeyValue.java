package com.toprunner.websphere.pmi;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRKeyValue {
  final static String version     = "  Version      : 1.0.1";
  final static String writtenBy   = "  Written By   : Tony Cao";
  final static String authorEmail = "  Email        : huycao69@gmail.com";
  final static String writtenDate = "  Written Date : Jan 2007";
  final static String bugs        = "  Please report any bug or enhancement\n  to the email above.  Thanks";
  final static String seperator   = "========================================";

  final static String PIPE="|";
  final static String COLON=":";
  final static String SEMICOLON=";";

  final static String LOGERROR="ERROR";
  final static String LOGWARN="WARN";
  final static String LOGINFO="INFO";

  final static String turnOn="yes";
  final static String medium="M";
  final static String high="H";
  final static String NA="na";
  final static String DOWN_ERROR="DOWN|DISABLE";

  final static String monitorSJStr="MONITORSERVLET";
  final static String monitorSSStr="MONITORSESSIONS";
  final static String monitorJVMStr="MONITORJVM";
  final static String monitorDSStr="MONITORDATASOURCE";

  final static String monitorDFStr="MONITORDEFAULTTHREADPOOL";
  final static String monitorHAMStr="MONITORHAMANAGERTHREADPOOL";
  final static String monitorMLStr="MONITORMESSAGELISTENERTHREADPOOL";
  final static String monitorORBStr="MONITORORBTHREADPOOL";
  final static String monitorPDStr="MONITORPROCESSDISCOVERYTHREADPOOL";
  final static String monitorSCStr="MONITORSOAPCONNECTORTHREADPOOL";
  final static String monitorDCSStr="MONITORTCPCHANNELDCSTHREADPOOL";
  final static String monitorWCStr="MONITORWEBCONTAINERTHREADPOOL";
  final static String monitorTMStr="MONITORTRANSACTIONMANAGER";
  final static String monitorWLMCStr="MONITORWORKLOADMANAGEMENTCLIENT";
  final static String monitorWLMSStr="MONITORWORKLOADMANAGEMENTServer";
  final static String monitorHAMgrStr="MONITORHAMANAGER";

  final static String sjAppList="SERVLETAPPNAMES";
  final static String ssAppList="SESSIONAPPNAMES";
  final static String appServersList="APPSERVERS";
  final static String nodesList="NODES";
  final static String datasourceList="DATASOURCES";

  final static String allServlet="ALL_SERVLET";
  final static String allJSP="ALL_JSP";
  final static String all="ALL";

  final static String outFileNameStr="DATAFILE";
  final static String errFileNameStr="ERRORLOGFILE";

  final static String monitorD="MONITORDURATION";
  final static String monitorI="MONITORINTERVAL";
  final static String monitorL="MONITORLEVEL";

  final static String mailhost="MAILHOST";
  final static String mailuserid="MAILUSERID";
  final static String mailpasswd="MAILPASSWD";
  final static String mailto="MAILTO";
  final static String mailfrom="MAILFROM";
  final static String mailcc="MAILCC";
  final static String mailbcc="MAILBCC";

  final static String SecurityEnableStr="SecurityEnable";
  final static String SecurityUserNameStr="SecurityUserName";
  final static String SecurityPasswdStr="SecurityPasswd";
  final static String TrustStoreFileStr="TrustStoreFile";
  final static String KeyStoreFileStr="KeyStoreFile";
  final static String TrustStorePasswdStr="TrustStorePasswd";
  final static String KeyStorePasswordStr="KeyStorePassword";

  final static String displaystr="DISPLAYONSCREEN";
  final static String resetvaluesstr="ResetValuesBeforeStart";

  final static String endPerReq = "ENDPERREQ";

  final static String inRaThoiStr = "InRaThoi";

  public TRKeyValue () {
  }
}

