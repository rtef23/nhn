package com.nhn.assignment.framework.server.servlet;

import com.nhn.assignment.framework.web.model.HttpRequest;

public interface ServletMappingInfo {

  boolean isHandleable(HttpRequest request);
}
