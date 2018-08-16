package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final LinkRepository linkRepository;
  private static final UserRepository userRepository;
  private static final VoteRepository voteRepository;
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  
    MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
  	linkRepository = new LinkRepository(mongo.getCollection("links"));
  	userRepository = new UserRepository(mongo.getCollection("users"));
  	voteRepository = new VoteRepository(mongo.getCollection("votes"));
  }

  public GraphQLEndpoint() {
    super(buildSchema());
  }

  private static GraphQLSchema buildSchema() {
  	if (debugIt) System.out.println("in GraphQLSchema-buildSchema()");
    return SchemaParser.newParser()
	          .file("schema.graphqls")  // parse schema we created
	          .resolvers(new Query(linkRepository), 
	                     new Mutation(linkRepository, userRepository, voteRepository),
	                     new SigninResolver(),
	                     new LinkResolver(userRepository),
	                     new VoteResolver(linkRepository, userRepository))
	          .scalars(Scalars.dateTime) // register new scalar 
	          .build()
	          .makeExecutableSchema();    
  }
  
  @Override
  protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
    User user = request
        .map(req -> req.getHeader("Authorization"))
        .filter(id -> !id.isEmpty())
        .map(id -> id.replace("Bearer ",  ""))
        .map(userRepository::findById)
        .orElse(null);
    if (debugIt) System.out.println("** GraphQLContext-createContext(), user is: " + 
                                     (user == null ? "null" : user.toString()));
    return new AuthContext(user, request, response);
  }
}