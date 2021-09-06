package com.nhn.assignment.framework.server.servlet;

import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class StaticResourceServlet implements Servlet {

  private final String docBase;
  private final String welcomeFile;

  public StaticResourceServlet(String docBase, String welcomeFile) {
    this.docBase = docBase;
    this.welcomeFile = welcomeFile;
  }

  @Override
  public void service(HttpRequest request, HttpResponse response) {
    Path uriPath = Paths.get(docBase).resolve("." + request.getUri());

    try {
      Writer writer = new OutputStreamWriter(response.getOutputStream());

      if (uriPath.toFile().exists()) {
        byte[] fileBytes = Files.readAllBytes(uriPath);

        sendHeader(writer, "HTTP/1.0 200 OK", fileBytes.length);

        Files.copy(uriPath, response.getOutputStream());
      } else {
        sendHeader(writer, "HTTP/1.0 404 NotFound", 0);
      }

    } catch (IOException e) {
    }
  }

  private void sendHeader(Writer writer, String stateMessage, int contentLength)
      throws IOException {
    writer.write(stateMessage + "\r\n");
    writer.write("Date: " + new Date() + "\r\n");
    writer.write("Server: JHTTP 2.0\r\n");
    writer.write("Content-length: " + contentLength + "\r\n");
    writer.write("Content-type: text/html\r\n\r\n");

    writer.flush();
  }
}
