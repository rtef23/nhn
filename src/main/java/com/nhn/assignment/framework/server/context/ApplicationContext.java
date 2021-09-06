package com.nhn.assignment.framework.server.context;

import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.web.model.HttpRequest;
import java.util.Map;

public interface ApplicationContext extends Context {

  boolean isHandleable(HttpRequest request);

  Servlet findHandleableServlet(HttpRequest request);

  Map<String, String> getErrorPageMap();
}
