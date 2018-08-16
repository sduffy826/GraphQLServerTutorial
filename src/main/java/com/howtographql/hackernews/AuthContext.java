package com.howtographql.hackernews;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.servlet.GraphQLContext;

public class AuthContext extends GraphQLContext {
  
  private final User user;
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  }  
  
  public AuthContext(User user, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
    super(request, response);
    this.user = user;
    if (debugIt) System.out.println("AuthContext-constructor: " + toString());
  }
  
  public User getUser() {
    if (debugIt) System.out.println("In AuthContext-getUser(), " + toString());
    return user;
  }  
  
  @Override
  public String toString() {
    return ( user == null ? "null user" : user.toString());
  }
}
