package com.nhn.assignment.framework.server;

import com.nhn.assignment.framework.server.context.ApplicationContext;
import com.nhn.assignment.framework.server.context.ServerContext;
import com.nhn.assignment.framework.server.exception.AvailableHostNotExistException;
import com.nhn.assignment.framework.server.model.ContentType;
import com.nhn.assignment.framework.server.model.StatusCode;
import com.nhn.assignment.framework.server.processor.RequestProcessor;
import com.nhn.assignment.framework.server.util.ResponseUtil;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

  private static final int NUM_THREADS = 50;

  private final ServerContext serverContext;

  public HttpServer(ServerContext serverContext) {
    this.serverContext = serverContext;
  }

  public void start() {
    serverContext.init();

    ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

    try (ServerSocket server = new ServerSocket(serverContext.getConfig().getPort())) {
      while (true) {
        Socket socket = server.accept();

        HttpRequest request = new HttpRequest(socket.getInputStream());
        HttpResponse response = new HttpResponse(socket.getOutputStream());

        try {
          ApplicationContext applicationContext = serverContext.getApplicationContexts()
              .stream()
              .filter(context -> context.isHandleable(request))
              .findFirst()
              .orElseThrow(AvailableHostNotExistException::new);

          pool.submit(new RequestProcessor(applicationContext, request,
              response, socket));
        } catch (AvailableHostNotExistException availableHostNotExistException) {
          ResponseUtil.sendResponseHeader(response.getOutputStream(), StatusCode.NotFound,
              ContentType.TextHtml, 0);

          socket.close();
        }
      }
    } catch (IOException e) {
    }
  }
}
