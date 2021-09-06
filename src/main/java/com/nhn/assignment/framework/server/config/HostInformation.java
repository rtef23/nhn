package com.nhn.assignment.framework.server.config;

import java.util.Map;

public class HostInformation {
  private String docBase;
  private String applicationRootPackage;
  private String welcomeFile;
  private Map<String, String> errorPageFiles;

  public String getDocBase() {
    return docBase;
  }

  public void setDocBase(String docBase) {
    this.docBase = docBase;
  }

  public String getApplicationRootPackage() {
    return applicationRootPackage;
  }

  public void setApplicationRootPackage(String applicationRootPackage) {
    this.applicationRootPackage = applicationRootPackage;
  }

  public String getWelcomeFile() {
    return welcomeFile;
  }

  public void setWelcomeFile(String welcomeFile) {
    this.welcomeFile = welcomeFile;
  }

  public Map<String, String> getErrorPageFiles() {
    return errorPageFiles;
  }

  public void setErrorPageFiles(Map<String, String> errorPageFiles) {
    this.errorPageFiles = errorPageFiles;
  }
}
