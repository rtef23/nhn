package com.nhn.assignment.framework.server;

import com.nhn.assignment.framework.server.context.ServerContext;

public class WebFramework {

  private static final String DEFAULT_CONFIG_LOCATION = "application.json";

  private WebFramework() {
  }

  public static void run() {
    new HttpServer(new ServerContext(DEFAULT_CONFIG_LOCATION)).start();
  }
}
