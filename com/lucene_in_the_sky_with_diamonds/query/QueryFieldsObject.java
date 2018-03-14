package com.lucene_in_the_sky_with_diamonds.query;

public class QueryFieldsObject {
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StringBuilder getContent() {
    return content;
  }

  public void setContent(StringBuilder content) {
    this.content = content;
  }

  public String id;
  public StringBuilder content;

  public QueryFieldsObject() {
    this.id = "";
    this.content = new StringBuilder();
  }

}
