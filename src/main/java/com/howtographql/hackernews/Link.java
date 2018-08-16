package com.howtographql.hackernews;

public class Link {
  private final String id;
  private final String url;
  private final String description;
  private final String userId;
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }

  public Link(String url, String description, String userId) {
    this(null, url, description, userId);
  }

  public Link(String id, String url, String description, String userId) {
    this.id = id;
    this.url = url;
    this.description = description;
    this.userId = userId;
    if (debugIt) System.out.println("Link-constructor: " + this.toString());
  }

  public String getId() {
    if (debugIt) System.out.println("Link-getId: " + id);
    return id;
  }

  public String getUrl() {
    if (debugIt) System.out.println("Link-getUrl: " + url);
    return url;
  }

  public String getDescription() {
    if (debugIt) System.out.println("Link-getDescription: " + description);
    return description;
  }
  
  public String getUserId() {
    if (debugIt) System.out.println("Link-getUserId: " + userId);
    return userId;    
  }

  @Override
  public String toString() {
    return "Link [id=" + id + ", url=" + url + ", description=" + description
        + ", userId=" + userId + "]";
  }
  
}