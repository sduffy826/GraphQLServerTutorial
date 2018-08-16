package com.howtographql.hackernews;

import java.time.ZonedDateTime;

public class Vote {
  private final String id;
  private final ZonedDateTime createdAt;
  private final String userId;
  private final String linkId;
  
  private static boolean debugIt = false;
  static {
    debugIt = DebugFlag.getFlag();  
  }

  public Vote(ZonedDateTime createdAt, String userId, String linkId) {
    this(null, createdAt, userId, linkId);
  }

  public Vote(String id, ZonedDateTime createdAt, String userId, String linkId) {
    this.id = id;
    this.createdAt = createdAt;
    this.userId = userId;
    this.linkId = linkId;
    if (debugIt) System.out.println("Vote-constructor: " + this.toString());
  }

  public String getId() {
    if (debugIt) System.out.println("Vote-getId: " + id);
    return id;
  }

  public ZonedDateTime getCreatedAt() {
    if (debugIt) System.out.println("Vote-getCreatedAt: " + createdAt.toString());
    return createdAt;
  }
  
  public String getUserId() {
    if (debugIt) System.out.println("Vote-getUserId: " + userId);
    return userId;
  }

  public String getLinkId() {
    if (debugIt) System.out.println("Vote-getLinkId: " + linkId);
    return linkId;
  }
  
  @Override
  public String toString() {
    return "Vote [id=" + id + ", createdAt=" + "createdAt.toString()" +
           ", userId=" + userId + ", linkId=" + linkId + "]";
  }
  
}