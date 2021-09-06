package com.nhn.assignment.application.simple;

import com.nhn.assignment.framework.server.model.ContentType;
import com.nhn.assignment.framework.server.model.StatusCode;
import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.server.util.ResponseUtil;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleServlet implements Servlet {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
    Writer writer = new OutputStreamWriter(response.getOutputStream());

    String responseString = LocalDateTime.now().format(DATE_TIME_FORMATTER);

    ResponseUtil.sendResponseHeader(response.getOutputStream(), StatusCode.Ok, ContentType.TextHtml
        , responseString.length());

    writer.write(responseString);
    writer.flush();
  }
}
