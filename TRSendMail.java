package com.toprunner.mail;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Authenticator;

import java.util.Vector;

import com.toprunner.parser.Parser;
import com.toprunner.mail.TRMailAuthenticator;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRSendMail {
  static private TRSendMail sendmail_instance = null;
  // private String cl = "TRSendMail";

  private TRSendMail () throws Exception {
  }

  static public TRSendMail getInstance() throws Exception {
    if (sendmail_instance == null)
      sendmail_instance = new TRSendMail();

    return sendmail_instance;
  } 
  
  static public TRSendMail getInstance(String smtphost) throws Exception {
    if (sendmail_instance == null)
      sendmail_instance = new TRSendMail();

    return sendmail_instance;
  } 

  /**
    *
   */
  public void sendMail(String smtphost, String login, String password, 
		       String from, String to, String cc, String bcc, 
                       String subject, String msgbody) throws Exception {
    // String fn = "sendMail";
    int i;
    Vector <String> vto;
    Vector <String> vcc;
    Vector <String> vbcc;
    // Parser P;

    if (to == null || to.equals("") || from  == null || from.equals("")) 
      return;

    if (smtphost == null || smtphost.equals("") || smtphost.trim().equals("NOTEXISTS")) 
      return;

    if (subject == null)
      subject = " ";

    if (msgbody == null)
      msgbody = " ";

    try {
      java.util.Properties properties = System.getProperties();
      properties.put("mail.smtp.host", smtphost);

      if (login != null && !login.equals("") && password != null && !password.equals(""))
        properties.put("mail.smtp.auth", "true");
      else
        properties.put("mail.smtp.auth", "false");

      Authenticator loAuthenticator = new TRMailAuthenticator(login, password); 
      Session session = Session.getInstance(properties, loAuthenticator);

      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));

      // for all the TO email addresses
      if (to != null && !to.equals("")) {
        vto = Parser.parseStringRetVect(to, "|");

        for (i = 0; i < vto.size(); i++) 
          message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)vto.elementAt(i)));
      }

      // for all the CC email addresses
      if (cc != null && !cc.equals("")) {
        vcc  = Parser.parseStringRetVect(cc, "|");

        if (vcc != null) {
          for (i = 0; i < vcc.size(); i++) 
            message.addRecipient(Message.RecipientType.CC, new InternetAddress((String)vcc.elementAt(i)));
        }
      }

      // for all the BCC email addresses
      if (bcc != null && !bcc.equals("")) {
        vbcc = Parser.parseStringRetVect(bcc, "|");

        if (vbcc != null) {
          for (i = 0; i < vbcc.size(); i++) 
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress((String)vbcc.elementAt(i)));
        }
      }

      message.setSubject(subject);
      message.setText(msgbody);
      Transport transport = session.getTransport("smtp");
      //transport.connect(smtphost);
      transport.connect(smtphost, login, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      message = null;
    }
    catch (MessagingException e) {
      throw new Exception (e.getMessage());
    }
  }

  public void usage () {
    System.out.println("");
    System.out.println("java TRSendMail <Email Server> <User Name> <Password> <To Email> <From Email> <CC Email> <BCC Email> <Subject> <Body>");
    System.out.println("");
  }

  public static void main (String args[]) {
    try {
      TRSendMail sm = TRSendMail.getInstance();

      if (args.length != 9) {
        sm.usage();
        System.exit(0);
      }

      System.out.println("Starting...");
      sm.sendMail(args[0], args[1], args[2], args[4], args[3], args[5], args[6], args[7], args[8]);
      System.out.println("Done");
    }
    catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}

