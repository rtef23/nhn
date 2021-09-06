package com.nhn.assignment.framework.server.processor;

import com.nhn.assignment.framework.server.context.ApplicationContext;
import com.nhn.assignment.framework.server.exception.ForbiddenResourceException;
import com.nhn.assignment.framework.server.exception.ResourceNotFoundException;
import com.nhn.assignment.framework.server.model.ContentType;
import com.nhn.assignment.framework.server.model.StatusCode;
import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.server.util.ResponseUtil;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestProcessor implements Runnable {

  private static final String NOT_FOUND_ERROR_PAGE = "404";
  private static final String FORBIDDEN_ERROR_PAGE = "403";
  private static final String INTERNAL_SERVER_ERROR_PAGE = "500";

  private final ApplicationContext applicationContext;
  private final HttpRequest request;
  private final HttpResponse response;
  private final Socket socket;

  public RequestProcessor(
      ApplicationContext applicationContext,
      HttpRequest request, HttpResponse response, Socket socket) {
    this.applicationContext = applicationContext;
    this.request = request;
    this.response = response;
    this.socket = socket;
  }

  @Override
  public void run() {
    Servlet servlet = applicationContext.findHandleableServlet(request);

    try {
      try {
        servlet.service(request, response);
      } catch (ForbiddenResourceException forbiddenResourceException) {
        sendErrorPage(response.getOutputStream(), StatusCode.Forbidden,
            applicationContext.getErrorPageMap().get(FORBIDDEN_ERROR_PAGE));
      } catch (ResourceNotFoundException resourceNotFoundException) {
        sendErrorPage(response.getOutputStream(), StatusCode.NotFound,
            applicationContext.getErrorPageMap().get(NOT_FOUND_ERROR_PAGE));
      } catch (Exception exception) {
        sendErrorPage(response.getOutputStream(), StatusCode.InternalServerError,
            applicationContext.getErrorPageMap().get(INTERNAL_SERVER_ERROR_PAGE));
      }
    } catch (IOException ioException) {

    } finally {
      try {
        socket.close();
      } catch (IOException e) {
      }
    }
  }

  private void sendErrorPage(OutputStream outputStream, StatusCode statusCode, String errorPage)
      throws IOException {
    byte[] errorPageBytes = Files.readAllBytes(
        Paths.get(errorPage));

    ResponseUtil.sendResponseHeader(outputStream,
        StatusCode.NotFound,
        ContentType.TextHtml, errorPageBytes.length);

    outputStream.write(errorPageBytes);
  }
}
