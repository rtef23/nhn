package com.nhn.assignment.framework.server.context;

import com.nhn.assignment.framework.server.config.HostInformation;
import com.nhn.assignment.framework.web.model.PackageBasedServletMappingInfo;
import com.nhn.assignment.framework.web.model.Servlet;
import com.nhn.assignment.framework.web.model.ServletMappingInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class HostBasedApplicationContext implements ApplicationContext {

  private final String host;
  private final HostInformation hostInformation;

  private Map<ServletMappingInfo, Servlet> servletMap;

  public HostBasedApplicationContext(String host,
      HostInformation hostInformation) {
    this.host = host;
    this.hostInformation = hostInformation;
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
}
