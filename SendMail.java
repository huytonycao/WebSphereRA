package com.toprunner.mail;

import java.util.*;

import com.toprunner.mail.TRSendMail;

/*
 * @author tony cao
 * @version 1.0
 */

public class SendMail extends Thread {
  Vector <String> vct;
  String userid = null;
  String password = null;
  String toemail = null;
  String fromemail = null;
  String ccemail = null;
  String bccemail = null;
  String smtp = null; 

  boolean startrun = true;

  public SendMail() {
    vct = new Vector <String> ();
  }
  
  public SendMail(String smtp, String userid, String password, String toemail, 
                  String fromemail, String ccemail, String bccemail) {
    vct = new Vector <String> ();
    this.smtp = smtp;
    this.userid = userid;
    this.password = password;
    this.toemail = toemail;
    this.fromemail = fromemail;
    this.ccemail = ccemail;
    this.bccemail = bccemail;
  }
  
  public void setMsg(String msg) {
    if (vct != null && msg != null && !msg.equals(""))
      vct.addElement(msg);
  }

  public void stopMail() {
    startrun = false;

    try {
      join(5);
    }
    catch (Exception e) {
    }
  }

  public void run() {
    try {
      TRSendMail sm = TRSendMail.getInstance();
      String msg = null;
      String sub = null;
      String body = null;
      String prev = "Starting";
      int count = 0;
      int len = 0;
      //int k = 0;

      while (startrun) {
        if (vct != null) {
          len = vct.size();

          for (int i = 0; i < len; i++) {
            if (!vct.isEmpty()) {

              msg = vct.elementAt(0);
              // 143.131.29.131

              if (msg != null && !msg.equals("")) {
                // count same message
                if (prev.equals(msg))
                  count++;

                if (!prev.equals(msg) || count >= 20) {
                  StringTokenizer stk = new StringTokenizer(msg, "|");
                  sub = null;
                  body = null;

                  if (stk.hasMoreTokens()) 
                    sub = stk.nextToken();

                  if (stk.hasMoreTokens()) {
                    if (count > 0)
                      body = stk.nextToken() + " [ resend every " + count / 20 + " minutes ]";
                    else
                      body = stk.nextToken() + " [ " + count + " ]";
                  }

                  if (smtp != null && !smtp.equals("") && toemail != null && !toemail.equals("") && 
                      fromemail != null && !fromemail.equals("")) {
                    sm.sendMail(smtp, userid, password, fromemail, toemail, ccemail, bccemail, sub, body);
                    prev = msg;
                    count = 0;
                  }
                }
              }

              vct.remove(0);
            }
        
            //k++;
          } 
        }
  
        sleep(15000);
      }
    }
    catch (Exception e) {
    }
  }
}
