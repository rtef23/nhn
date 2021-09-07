package com.nhn.assignment.framework.web.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

public class HttpRequestTest {

  @Test
  public void parseStream() throws IOException {
    //given
    String dummyRequest = "GET /test-uri HTTP/1.0\r\n"
        + "Host: localhost\r\n\r\n";

    InputStream inputStream = new ByteArrayInputStream(dummyRequest.getBytes());

    //when
    HttpRequest sut = new HttpRequest(inputStream);

    //then
    Assert.assertEquals("/test-uri", sut.getUri());
    Assert.assertEquals("GET", sut.getMethod());
    Assert.assertEquals("HTTP/1.0", sut.getProtocol());

    Assert.assertEquals("localhost", sut.getHost());
  }
}