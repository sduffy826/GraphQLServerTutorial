package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.List;

public class Query implements GraphQLRootResolver {

  private final LinkRepository linkRepository;
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }

  public Query(LinkRepository linkRepository) {
  	this.linkRepository = linkRepository;
  	if (debugIt) System.out.println("in Query-constructor");
  }

  public List<Link> allLinks() {
    if (debugIt) System.out.println("in Query-allLinks()");
  	return linkRepository.getAllLinks();
  }

}