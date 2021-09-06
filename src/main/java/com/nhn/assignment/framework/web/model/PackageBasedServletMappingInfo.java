package com.nhn.assignment.framework.web.model;

public class PackageBasedServletMappingInfo implements ServletMappingInfo {

  private final String mappingUri;

  public PackageBasedServletMappingInfo(String applicationRootPackage, Class<?> clazz) {
    this.mappingUri = clazz.getName().replace(applicationRootPackage + ".", "");
  }

  @Override
  public boolean isHandleable(Request request) {
    return false;
  }
}
