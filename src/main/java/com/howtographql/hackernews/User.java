package com.howtographql.hackernews;

public class User {

  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  }
  
  private final String id;
  private final String name;
  private final String email;
  private final String password;
  
  public User(String name, String email, String password) {     
    this(null, name, email, password);
    if (debugIt) System.out.println("User-constructor: id: null name: " + name + " email: " + email + " password: " + password);
  }
  
  public User(String id, String name, String email, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }
  
  public String getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getPassword() {
    return password;
  }

  public String toString() {
    return "id: " + getId() + " name: " + getName() + " email: " + getEmail() + " pw: " + getPassword();
  }
}
