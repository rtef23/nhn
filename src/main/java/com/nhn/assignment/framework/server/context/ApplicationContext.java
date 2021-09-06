package com.nhn.assignment.framework.server.context;

import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.web.model.HttpRequest;

public interface ApplicationContext extends Context {

  boolean isHandleable(HttpRequest request);

  Servlet findHandleableServlet(HttpRequest request);
}
