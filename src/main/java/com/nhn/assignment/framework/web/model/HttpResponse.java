package com.nhn.assignment.framework.web.model;

import java.io.OutputStream;

public class HttpResponse implements Response {

  private OutputStream outputStream;

  public HttpResponse(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public OutputStream getOutputStream() {
    return outputStream;
  }
}
