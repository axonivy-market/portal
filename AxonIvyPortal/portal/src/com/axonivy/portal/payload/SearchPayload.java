package com.axonivy.portal.payload;

public class SearchPayload {
  private String query;

  public String getQuery() {
    return query.trim();
  }

  public void setQuery(String query) {
    this.query = query;
  }

}
