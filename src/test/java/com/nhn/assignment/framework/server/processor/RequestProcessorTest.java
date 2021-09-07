package com.nhn.assignment.framework.server.processor;

import com.nhn.assignment.framework.server.context.ApplicationContext;
import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.net.Socket;
import org.junit.Test;
import org.mockito.Mockito;

public class RequestProcessorTest {

  @Test
  public void runTest() throws Exception {
    //given
    ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
    HttpRequest httpRequest = Mockito.mock(HttpRequest.class);
    HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
    Socket socket = Mockito.mock(Socket.class);

    Servlet testServlet = Mockito.mock(Servlet.class);

    Mockito.when(applicationContext.findHandleableServlet(Mockito.eq(httpRequest)))
        .thenReturn(testServlet);

    RequestProcessor sut = new RequestProcessor(applicationContext, httpRequest, httpResponse,
        socket);

    //when
    sut.run();

    //then
    Mockito.verify(testServlet).service(Mockito.eq(httpRequest), Mockito.eq(httpResponse));
  }
}