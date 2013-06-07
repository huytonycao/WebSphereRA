package com.toprunner.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/*
 * @author tony cao
 * @version 1.0
 */

public class TRMailAuthenticator extends Authenticator {
  String username, password;

  public TRMailAuthenticator (String un, String pw) {
    username = un;
    password = pw;
  }

  public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
  }
}
