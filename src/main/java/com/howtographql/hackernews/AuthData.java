package com.howtographql.hackernews;

public class AuthData {
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  }
  
  private String email;
  private String password;
  
  public AuthData() { }
  
  public AuthData(String email, String password) {
    this.email = email;
    this.password = password;
    if (debugIt) System.out.println("Constructor AuthData: " + this.toString());
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String toString() {
    return "email: " + getEmail() + " password: " + getPassword();
  }
}
