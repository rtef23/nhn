package com.nhn.assignment.framework.web.model;

import com.nhn.assignment.framework.server.servlet.ServletMappingInfo;
import org.apache.commons.lang3.StringUtils;

public class PackageBasedServletMappingInfo implements ServletMappingInfo {

  private final String mappingUri;

  public PackageBasedServletMappingInfo(String applicationRootPackage, Class<?> clazz) {
    this.mappingUri = "/" + clazz.getName().replace(applicationRootPackage + ".", "");
  }

  @Override
  public boolean isHandleable(HttpRequest request) {
    return StringUtils.equals(request.getUri(), mappingUri);
  }
}
