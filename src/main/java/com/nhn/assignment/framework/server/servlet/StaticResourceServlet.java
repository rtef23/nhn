package com.nhn.assignment.framework.server.servlet;

import com.nhn.assignment.framework.server.exception.ForbiddenResourceException;
import com.nhn.assignment.framework.server.exception.ResourceNotFoundException;
import com.nhn.assignment.framework.server.model.ContentType;
import com.nhn.assignment.framework.server.model.StatusCode;
import com.nhn.assignment.framework.server.util.ResponseUtil;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;

public class StaticResourceServlet implements Servlet {

  private final String docBase;

  public StaticResourceServlet(String docBase) {
    this.docBase = docBase;
  }

  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
    Path uriPath = Paths.get(docBase).resolve("." + request.getUri());

    if (StringUtils.contains(request.getUri(), "../")) {
      throw new ForbiddenResourceException();
    }

    if (!uriPath.toFile().exists()) {
      throw new ResourceNotFoundException();
    }

    byte[] fileBytes = Files.readAllBytes(uriPath);

    ResponseUtil
        .sendResponseHeader(response.getOutputStream(), StatusCode.Ok, ContentType.TextHtml,
            fileBytes.length);

    response.getOutputStream().write(fileBytes);
  }
}
