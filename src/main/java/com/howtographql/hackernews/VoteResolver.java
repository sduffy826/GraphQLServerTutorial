package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Contains vote to sub-queries
 */
public class VoteResolver implements GraphQLResolver<Vote>{
  
  private final LinkRepository linkRepository;
  private final UserRepository userRepository;
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }
  
  VoteResolver(LinkRepository linkRepository, UserRepository userRepository) {
    this.linkRepository = linkRepository;
    this.userRepository = userRepository;
    if (debugIt) System.out.println("In VoteResolver-constructor");
  }
  
  public User user(Vote vote) {
    if (debugIt) System.out.println("VoteResolver-user, vote:" + (vote == null ? "null" : vote.toString()));
    if (vote.getUserId() == null) {
      return null;
    }
    return userRepository.findById(vote.getUserId());
  }

  public Link link(Vote vote) {
    if (debugIt) System.out.println("VoteResolver-link, vote:" + (vote == null ? "null" : vote.toString()));
    if (vote.getLinkId() == null) {
      return null;
    }
    return linkRepository.findById(vote.getLinkId());
  }
}
