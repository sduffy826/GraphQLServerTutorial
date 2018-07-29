package com.howtographql.hackernews;

import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.servlet.SimpleGraphQLServlet;
import graphql.servlet.GraphQLContext;
import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
	public GraphQLEndpoint() {
    super(buildSchema());
	}

  private static GraphQLSchema buildSchema() {
  	LinkRepository linkRepository = new LinkRepository();
  	return SchemaParser.newParser()
	          .file("schema.graphqls")  // parse schema we created
	          .resolvers(new Query(linkRepository), new Mutation(linkRepository))
	          .build()
	          .makeExecutableSchema();
  }
}