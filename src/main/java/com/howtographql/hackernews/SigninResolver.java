package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class SigninResolver implements GraphQLResolver<SigninPayload> {
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }
  
  public User user(SigninPayload payload) {
    if (debugIt) System.out.println("In SigninResolver-user, user: " + payload.getUser().toString());
    return payload.getUser();
  }
}
