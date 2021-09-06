package com.nhn.assignment.framework.server.model;

public enum StatusCode {
  Ok("HTTP/1.0 200 OK"),
  NotFound("HTTP/1.0 404 Not Found"),
  Forbidden("HTTP/1.0 403 Forbidden"),
  InternalServerError("HTTP/1.0 500 Not Found");

  private String stateMessage;

  StatusCode(String stateMessage) {
    this.stateMessage = stateMessage;
  }

  public String getStateMessage() {
    return stateMessage;
  }
}
