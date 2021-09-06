package com.nhn.assignment.framework.server.context;

import com.nhn.assignment.framework.server.config.HostInformation;
import com.nhn.assignment.framework.server.servlet.Servlet;
import com.nhn.assignment.framework.server.servlet.ServletMappingInfo;
import com.nhn.assignment.framework.server.servlet.StaticResourceServlet;
import com.nhn.assignment.framework.web.model.HttpRequest;
import com.nhn.assignment.framework.web.model.PackageBasedServletMappingInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class HostBasedApplicationContext implements ApplicationContext {

  private final String host;
  private final HostInformation hostInformation;
  private final Servlet staticResourceServlet;

  private Map<ServletMappingInfo, Servlet> servletMap;

  public HostBasedApplicationContext(String host,
      HostInformation hostInformation) {
    this.host = host;
    this.hostInformation = hostInformation;
    this.staticResourceServlet = new StaticResourceServlet(hostInformation.getDocBase());
  }

  @Override
  public void init() {
    this.servletMap = loadServletMap(hostInformation.getApplicationRootPackage());
  }

  private Map<ServletMappingInfo, Servlet> loadServletMap(String applicationRootPackage) {
    Reflections reflections = new Reflections(applicationRootPackage, new SubTypesScanner(false));

    List<Class<?>> servletClasses = new ArrayList<>(reflections.getSubTypesOf(Servlet.class));
    Map<ServletMappingInfo, Servlet> result = new HashMap<>();

    for (Class<?> servletClass : servletClasses) {
      try {
        Servlet instantiatedServlet = (Servlet) servletClass.newInstance();

        result.put(new PackageBasedServletMappingInfo(applicationRootPackage, servletClass),
            instantiatedServlet);
      } catch (InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    return result;
  }

  @Override
  public boolean isHandleable(HttpRequest request) {
    return StringUtils.startsWith(request.getHost(), host);
  }

  @Override
  public Servlet findHandleableServlet(HttpRequest request) {
    return servletMap.keySet()
        .stream()
        .filter(servletMappingInfo -> servletMappingInfo.isHandleable(request))
        .map(servletMappingInfo -> servletMap.get(servletMappingInfo))
        .findFirst()
        .orElse(staticResourceServlet);
  }

  @Override
  public Map<String, String> getErrorPageMap() {
    return this.hostInformation.getErrorPageFiles();
  }
}
