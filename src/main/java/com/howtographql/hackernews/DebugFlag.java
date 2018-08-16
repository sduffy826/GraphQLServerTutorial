package com.howtographql.hackernews;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class DebugFlag {
  static String propName = "local.properties";
  static Properties properties = null;
  
  public static boolean getFlag() {    
    boolean rtnFlag = false;
    properties = new Properties();
    try (InputStream is = new FileInputStream(propName))  {      
      properties.load(is);
      String debugString = properties.getProperty("debug");
      if ( debugString != null & debugString.equalsIgnoreCase("true")) {
        rtnFlag = true;        
      }
    }
    catch (IOException e) {
      // nothing needed
    }
    return rtnFlag;
  }  
  
  public static void setFlag(boolean debugFlag) {   
    boolean tempFlag = getFlag();
    if (tempFlag != debugFlag) {
      try (OutputStream os = new FileOutputStream(propName))  {
        properties.setProperty("debug", debugFlag ? "true" : "false");
        properties.store(os, null);
        System.out.println("wrote properties");
      }
      catch (IOException e) {
        // nuttin needed
      }
    }
    return;
  }  
}
