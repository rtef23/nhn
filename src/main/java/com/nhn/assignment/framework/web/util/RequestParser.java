package com.nhn.assignment.framework.web.util;

import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.Request;
import java.io.InputStream;

public class RequestParser {

  private RequestParser() {
  }

  public static Request parse(InputStream inputStream) {
    return new HttpRequest();
  }
}
