package com.nhn.assignment.framework.web.model;

import java.io.Writer;

public class HttpResponse implements Response {

  private Writer writer;

  public Writer getWriter() {
    return writer;
  }
}
