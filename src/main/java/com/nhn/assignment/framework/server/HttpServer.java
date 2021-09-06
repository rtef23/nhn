package com.nhn.assignment.framework.server;

import com.nhn.assignment.framework.server.context.ServerContext;
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
//          Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
//          pool.submit(r);
        } catch (IOException ex) {
        }
      }
    } catch (IOException e) {
    }
  }
}
