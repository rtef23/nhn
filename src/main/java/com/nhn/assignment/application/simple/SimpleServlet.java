package com.nhn.assignment.application.simple;

import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import com.nhn.assignment.framework.web.model.Servlet;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleServlet implements Servlet {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @Override
  public void service(HttpRequest request, HttpResponse response) {
    try {
      Writer writer = response.getWriter();

      writer.write(LocalDateTime.now().format(DATE_TIME_FORMATTER));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
