package com.nhn.assignment.framework.server.model;

public enum ContentType {
  TextHtml("text/html");

  private String value;

  ContentType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
