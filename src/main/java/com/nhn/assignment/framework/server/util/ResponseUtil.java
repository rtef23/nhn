package com.nhn.assignment.framework.server.util;

import com.nhn.assignment.framework.server.model.ContentType;
import com.nhn.assignment.framework.server.model.StatusCode;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class ResponseUtil {

  private ResponseUtil() {
  }

  public static void sendResponseHeader(OutputStream outputStream, StatusCode statusCode,
      ContentType contentType,
      int contentLength)
      throws IOException {
    outputStream.write((statusCode.getStateMessage() + "\r\n").getBytes());
    outputStream.write(("Date: " + new Date() + "\r\n").getBytes());
    outputStream.write("Server: JHTTP 2.0\r\n".getBytes());
    outputStream.write(("Content-length: " + contentLength + "\r\n").getBytes());
    outputStream.write(("Content-type: " + contentType.getValue() + "\r\n\r\n").getBytes());

    outputStream.flush();
  }
}
