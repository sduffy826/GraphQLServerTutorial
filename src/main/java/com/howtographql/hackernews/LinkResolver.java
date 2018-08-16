package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Contains link to sub-queries
 */
public class LinkResolver implements GraphQLResolver<Link>{
  private final UserRepository userRepository;
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }
  
  LinkResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
    if (debugIt) System.out.println("In LinkResolver-constructor");
  }
  
  public User postedBy(Link link) {
    if (debugIt) System.out.println("LinkResolver-postedBy: " + (link.getUserId() == null ? "null" : 
                                     userRepository.findById(link.getUserId())));
    if (link.getUserId() == null) {
      return null;
    }
    return userRepository.findById(link.getUserId());
  }

}
