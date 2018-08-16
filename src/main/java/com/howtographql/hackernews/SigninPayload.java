package com.howtographql.hackernews;

/**
 * Represents a logged in user and the token to be used for authentication on subsequent requests
 */
public class SigninPayload {
  private String token;
  private final User user;
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }

  
  public SigninPayload(String token, User user) {
    this.token = token;
    this.user = user;
    if (debugIt) System.out.println("in SigninPayload-constructor");
  }
  
  public String getToken() {
    if (debugIt) System.out.println("in SigninPayload-getToken, token: " + token);
    return token;
  }
  
  public User getUser() {
    if (debugIt) System.out.println("in SigninPayload-getUser, user: " + user.toString());
    return user;
  }
}
