package com.howtographql.hackernews;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class LinkRepository {
  private final MongoCollection<Document> links;
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }

  public LinkRepository(MongoCollection<Document> links) {
    this.links = links;

    if (debugIt) System.out.println("LinkRepository-constructor");
  }

  public Link findById(String id) {
    Document doc = links.find(eq("_id", new ObjectId(id))).first();
    if (debugIt) System.out.println("LinkRepository-findById: " + doc.get("_id").toString());
    return link(doc);
  }

  public List<Link> getAllLinks() {
    
    List<Link> allLinks = new ArrayList<>();
    for (Document doc : links.find()) {
      allLinks.add(link(doc));
    }
    if (debugIt) System.out.println("LinkRepository-getAllLinks(), size: " + Integer.toString(allLinks.size()));
  	return allLinks;
  }

  public void saveLink(Link link) {
    Document doc = new Document();
    doc.append("url", link.getUrl());
    doc.append("description", link.getDescription());
    doc.append("postedBy",  link.getUserId());
  	links.insertOne(doc);
  	if (debugIt) System.out.println("LinkRepository-saveLink: " + link.toString());
  }

  private Link link(Document doc) {
    return new Link(
       doc.get("_id").toString(),
       doc.getString("url"),
       doc.getString("description"),
       doc.getString("postedBy"));
  }
}