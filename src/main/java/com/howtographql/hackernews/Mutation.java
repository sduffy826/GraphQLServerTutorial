package com.howtographql.hackernews;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  }  

  private final LinkRepository linkRepository;
  private final UserRepository userRepository;
  private final VoteRepository voteRepository;

  public Mutation(LinkRepository linkRepository, UserRepository userRepository, VoteRepository voteRepository) {
  	this.linkRepository = linkRepository;
  	this.userRepository = userRepository;
  	this.voteRepository = voteRepository;

    if (debugIt) System.out.println("just invokved Mutation constructor");
  }

  public Link createLink(String url, String description, DataFetchingEnvironment env) {
    if (debugIt) System.out.println("in Mutation-createLink, url: " + url + 
                                    " description:" + description +
                                    " env: " + env.toString());
   
    AuthContext context = env.getContext();    
    if (debugIt) System.out.println("Mutation-createLink, got context: " + context.toString());
      	
    Link newLink = new Link(url, description, context.getUser().getId());  	
    if (debugIt) System.out.println("Mutation-createLink, Link created: " + newLink.toString());
  	
  	linkRepository.saveLink(newLink);
  	if (debugIt) System.out.println("Mutation-createLink, after linkRepository.saveLink()");
  	
  	return newLink;
  }
  
  public User createUser(String name, AuthData auth) {    
    if (debugIt) System.out.println("in Mutation-createUser, name: " + name + " auth:" + auth.toString());
    
    User newUser = new User(name, auth.getEmail(), auth.getPassword());    
    return userRepository.saveUser(newUser);
  }
  
  public SigninPayload signinUser(AuthData auth) {
    if (debugIt) System.out.println("Mutation-signinUser, auth: " + auth.toString());
    User user = userRepository.findByEmail(auth.getEmail());
    
    if (debugIt) System.out.println("Mutation-signinUser, got user: " + ( user == null ? "null" : user.toString()));
    
    if (user.getPassword().equals(auth.getPassword())) {
      return new SigninPayload(user.getId(), user);
    }
    if (debugIt) System.out.println("Throwing exception");
    throw new GraphQLException("Invalid credentials");
  }
  
  public Vote createVote(String linkId, String userId) {
    ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
    return voteRepository.saveVote(new Vote(now, userId, linkId));
  }  
}