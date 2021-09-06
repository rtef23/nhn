package com.nhn.assignment.framework.server.servlet;

import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;

public interface Servlet {

  void service(HttpRequest request, HttpResponse response) throws Exception;
}
