package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {

  private final LinkRepository linkRepository;

  public Mutation(LinkRepository linkRepository) {
  	this.linkRepository = linkRepository;
  }

  public Link createLink(String url, String description) {
  	Link newLink = new Link(url, description);
  	linkRepository.saveLink(newLink);
  	return newLink;
  }

}