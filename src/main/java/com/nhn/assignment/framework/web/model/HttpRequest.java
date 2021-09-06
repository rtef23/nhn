package com.nhn.assignment.framework.web.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class HttpRequest implements Request {

  private static final String HOST_HEADER_KEY = "HOST";
  private static final String HTTP_HEADER_SEPARATOR = ":";

  private final String method;
  private final String uri;
  private final String protocol;
  private final Map<String, String> headers;

  public HttpRequest(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    String[] tokens = StringUtils.split(reader.readLine(), " ");

    this.method = tokens[0];
    this.uri = tokens[1];
    this.protocol = tokens[2];
    this.headers = parseHeaders(reader);
  }

  private Map<String, String> parseHeaders(BufferedReader reader) throws IOException {
    Map<String, String> result = new HashMap<>();
    String requestLine;

    while ((requestLine = reader.readLine()) != null) {
      if (StringUtils.isEmpty(requestLine)) {
        break;
      }

      String headerKey = StringUtils.upperCase(StringUtils
          .trim(StringUtils.substringBefore(requestLine, HTTP_HEADER_SEPARATOR)));
      String headerValue = StringUtils
          .trim(StringUtils.substringAfter(requestLine, HTTP_HEADER_SEPARATOR));

      result.put(headerKey, headerValue);
    }

    return result;
  }

  public String getHost() {
    return headers.get(HOST_HEADER_KEY);
  }

  public String getMethod() {
    return method;
  }

  public String getUri() {
    return uri;
  }

  public String getProtocol() {
    return protocol;
  }
}
