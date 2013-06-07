package com.toprunner.websphere.pmi;

import java.util.*;
// import java.lang.*;
import java.io.*;

import com.toprunner.websphere.pmi.TRKeyValue;
import com.toprunner.mail.SendMail;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRWSPMIUtils {
  static TRWSPMIUtils instance = null;

  String outfile = null;
  String errfile = null;
  PrintWriter out = null;
  PrintWriter err = null;

  // mail stuff
  String mailhoststr = null;
  String mailuseridstr = null;
  String mailpasswdstr = null;
  String mailtostr = null;
  String mailfromstr = null;
  String mailccstr = null;
  String mailbccstr = null;

  boolean display = false;
 
  // mail
  SendMail sm;
  StringBuffer emailstr = null;
  boolean errorOccured = false;

  protected TRWSPMIUtils (String outfile, String errfile, boolean display, String mailhoststr,
                          String mailuseridstr, String mailpasswdstr, String mailtostr,
                          String mailfromstr, String mailccstr, String mailbccstr) {
    this.outfile = outfile;
    this.errfile = errfile;
    this.display = display;
    this.mailhoststr = mailhoststr;
    this.mailuseridstr = mailuseridstr;
    this.mailpasswdstr = mailpasswdstr;
    this.mailtostr = mailtostr;
    this.mailfromstr = mailfromstr;
    this.mailccstr = mailccstr;
    this.mailbccstr = mailbccstr;

    openOutputFile(this.outfile, 1);  
    openOutputFile(this.errfile, 2);  

    emailstr = new StringBuffer("");

    if (mailhoststr != null && !mailhoststr.equals("")) {
      sm = new SendMail(mailhoststr, mailuseridstr, mailpasswdstr, mailtostr, mailfromstr, mailccstr, mailbccstr);
      sm.start();
    }
  }

  public static TRWSPMIUtils getInstance() {
    return instance;
  }

  public static TRWSPMIUtils getInstance(String outfile, String errfile, boolean display, String mailhoststr,
                          String mailuseridstr, String mailpasswdstr, String mailtostr,
                          String mailfromstr, String mailccstr, String mailbccstr) {
    if (instance == null) 
      instance = new TRWSPMIUtils (outfile, errfile, display, mailhoststr, mailuseridstr, mailpasswdstr,
                                   mailtostr, mailfromstr, mailccstr, mailbccstr);
    
    return instance;
  }

  public void openOutputFile(String filename, int type) {
    // final String fn = "openOutputFile";
     
    try {
      if (type == 1) {
        FileWriter fstream = new FileWriter(filename);
        out = new PrintWriter(new BufferedWriter(fstream), true);
      }
      else if (type == 2) {
        FileWriter fstream = new FileWriter(filename, true);
        err = new PrintWriter(new BufferedWriter(fstream), true);
      }
    } 
    catch (Exception e) {
      System.err.println("File output error: " + filename);
      System.err.println("File output error: " + filename);
      e.printStackTrace();
      
      cleanUp();
    }
  }

  public void cleanUp() {
    if (out != null) 
      out.close();

    if (err != null) 
      err.close();

    if (sm != null)
      sm.stopMail();
  }

  public void printLogMsg(String function, String level, String message) {
    // final String fn = "printLogMsg";

    if (err != null) {
      err.print(getDate() + " " + getTime() + " [" + function + "] [" + level + "] " + message + "\n");
      err.flush();
    }
  }

  public void printStackTrace(String function, String level, Exception est) {
    // final String fn = "printStackTrace";

    printLogMsg(function, TRKeyValue.LOGERROR, "Exception:");

    if (err != null) {
      est.printStackTrace(err);
      err.flush();
    }
  }

  public void printOutMsg(String message) {
    // final String fn = "printOutMsg";

    // display on screen
    if (display)
      System.out.println(message);

    if (out != null) {
      out.print(message + "\n");
      out.flush();
    }
  }

  public String zeroPad(int number) {
    // final String fn = "zeroPad";

    if (number >= 0 && number <= 9)
      return "0" + number;

    return "" + number;
  }

  public String getDate() {
    Calendar c = Calendar.getInstance();
    String date = String.format("%1$tm/%1$td/%1$ty", c);

    return date;
  }
 
  public String getTime() {
    Calendar c = Calendar.getInstance();
    String time = String.format("%1$tH:%1$tM:%1$tS:%1$tL", c);

    return time;
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

  public void emailMsg(String str, int type) {
    final String fn = "emailMsg";

    if (emailstr == null)
      emailstr = new StringBuffer("");

    if (type == 0) {
      if (emailstr != null)
        emailstr.append(str).append("\n");
    }
    else if (type == 1) {
      // if there is an error string, send it first
      if (emailstr != null && !(emailstr.toString()).equals("")) {
        if (sm != null) 
          sm.setMsg("Error|" + emailstr.toString());

        printLogMsg(fn, TRKeyValue.LOGINFO, "Send email alert if it is enable.  Error|" + emailstr.toString());
        emailstr = null;
        errorOccured = true;
      } // there is no error string, send resolve
      else if (str.equals(TRKeyValue.endPerReq) && errorOccured) {
        if (sm != null) 
          sm.setMsg("Resolved|Server(s) are back to normal");

        printLogMsg(fn, TRKeyValue.LOGINFO, "Send email alert if it is enable.  Resolved|Server(s) are back to normal");
        errorOccured = false;
      }
    }
  }
}

