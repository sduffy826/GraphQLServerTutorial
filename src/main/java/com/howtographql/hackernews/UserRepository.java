package com.howtographql.hackernews;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UserRepository {
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();   
  }  
  
  private final MongoCollection<Document> users;

  public UserRepository(MongoCollection<Document> users) {
    this.users = users;
  }

  public User findByEmail(String email) {
    if (debugIt) System.out.println("UserRepository-findByEmail with email: " + email);
    Document doc = users.find(eq("email", email)).first();    
    return user(doc);
  }

  public User findById(String id) {
    if (debugIt) System.out.println("UserRepository-findById with id: " + id);
    Document doc = users.find(eq("_id", new ObjectId(id))).first();
    return user(doc);
  }
  
  public User saveUser(User user) {
    Document doc = new Document();
    doc.append("name",  user.getName());
    doc.append("email", user.getEmail());
    doc.append("password",  user.getPassword());
    users.insertOne(doc);
    if (debugIt) {
      System.out.println("UserRepository-saveUser, just saved record, doc below");

      System.out.println("Id is " + doc.get("_id").toString());
      System.out.println("Name is " + doc.getString("name"));
      System.out.println("EMail is " + doc.getString("email"));
      System.out.println("Password is " + doc.getString("password"));
      
      System.out.println("Calling UserRepository-user(doc)");
    }            
    return user(doc);
  }
  
  private User user(Document doc) {
    if (debugIt) System.out.println("In UserRepository-user doc: " + doc);
    if (doc == null) {
      return null;
    }
    User aUser = new User(doc.get("_id").toString(),
                          doc.getString("name"),
                          doc.getString("email"),
                          doc.getString("password"));    
    if (debugIt) System.out.println("UserRepository-user, after instantiation: " + aUser.toString());
    return aUser;
  }
}