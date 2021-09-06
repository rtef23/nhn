package com.nhn.assignment.application.simple;

import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimpleServlet implements Servlet {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @Override
  public void service(HttpRequest request, HttpResponse response) {
    try {
      Writer writer = new OutputStreamWriter(response.getOutputStream());

      String responseString = LocalDateTime.now().format(DATE_TIME_FORMATTER);

      sendHeader(writer, responseString.length());

      writer.write(responseString);
      writer.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendHeader(Writer writer, int length) throws IOException {
    writer.write("HTTP/1.0 " + 200 + " OK\r\n");
    writer.write("Date: " + new Date() + "\r\n");
    writer.write("Server: JHTTP 2.0\r\n");
    writer.write("Content-length: " + length + "\r\n");
    writer.write("Content-type: text/html\r\n\r\n");

    writer.flush();
  }
}
