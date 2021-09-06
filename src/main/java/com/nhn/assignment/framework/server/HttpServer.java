package com.nhn.assignment.framework.server;

import com.nhn.assignment.framework.server.context.ApplicationContext;
import com.nhn.assignment.framework.server.context.ServerContext;
import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

  private static final int NUM_THREADS = 50;

  private ServerContext serverContext;

  public HttpServer(ServerContext serverContext) {
    this.serverContext = serverContext;
  }

  public void start() {
    serverContext.init();

    ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

    try (ServerSocket server = new ServerSocket(serverContext.getConfig().getPort())) {
      while (true) {
        try {
          Socket socket = server.accept();

          HttpRequest request = new HttpRequest(socket.getInputStream());
          HttpResponse response = new HttpResponse(socket.getOutputStream());

          ApplicationContext applicationContext = serverContext.getApplicationContexts()
              .stream()
              .filter(context -> context.isHandleable(request))
              .findFirst()
              .orElseThrow(RuntimeException::new);

          dispatch(applicationContext, request, response);

          socket.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    } catch (IOException e) {
    }
  }

  private void dispatch(ApplicationContext context, HttpRequest request, HttpResponse response) {
    Servlet servlet = context.findHandleableServlet(request);

    servlet.service(request, response);
  }
}
