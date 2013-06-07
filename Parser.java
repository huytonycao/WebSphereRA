package com.toprunner.parser;

import java.util.Vector;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.lang.String;
import java.lang.StringBuffer;

/*
 * @author tony cao
 * @version 1.0
 */
 
public class Parser {
  // Constructors
  /**
    * Creates a Parser object
    *
   */
  public Parser () {
  }

  // Methods
  /**
    * Parses a string and returns a vector as a result
    * @param str   String needs to be parsed.
    * @param delim Deliminator 
    * @return Vector
   */
  public static Vector <String> parseStringRetVect (String str, String delim) {
    if (str == null || str.equals(""))
      return null;

    StringTokenizer st  = new StringTokenizer (str, delim);
    Vector <String> vct = new Vector <String> ();
    String          tmp;
 
    while (st.hasMoreTokens()) {
      tmp = (st.nextToken()).trim();

      if (!tmp.equals(""))
        vct.addElement(tmp);
    }
 
    st = null;
    return vct;
  }
 
  /**
    * Parses a string and returns a hashtable as a result
    * @param str     String needs to be parsed.
    * @param pdelim  Deliminator for a tag value pair
    * @param tvdelim Deliminator for tag
    * @return Vector
   */
  public static Hashtable <Object, Object> parseStringRetHash (String str, String pdelim, String tvdelim) {
    if (str    == null || str.equals("") || tvdelim == null || tvdelim.equals("") || 
        pdelim == null || pdelim.equals(""))
      return null;

    Hashtable <Object, Object> retht = new Hashtable <Object, Object> ();

    if (str != null && str.length() > 0) {
      StringTokenizer st = new StringTokenizer (str, pdelim);
      String fkey        = new String();
      String fvalue      = new String();
      String tmp;
 
      while (st.hasMoreTokens()) {
        StringBuffer value = new StringBuffer();
        tmp = st.nextToken();
 
        StringTokenizer st1 = new StringTokenizer (tmp, tvdelim);
 
        if (st1.hasMoreTokens()) 
          fkey   = (st1.nextToken()).trim();
 
        while (st1.hasMoreTokens()) {
          value.append(st1.nextToken());
 
          if (st1.hasMoreTokens())
            value.append(tvdelim);
        }

        if (value != null && !value.equals(""))
          fvalue = (value.toString()).trim();
 
        if (fvalue == null)
          fvalue = " ";
 
        if ((fkey.length() > 0) && (fvalue.length() > 0))
          retht.put((Object)fkey, (Object)fvalue);
      }
    }
 
    return retht;
  }
}

