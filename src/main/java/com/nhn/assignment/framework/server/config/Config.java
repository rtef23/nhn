package com.nhn.assignment.framework.server.config;

import java.util.Map;

public class Config {

  private int port;
  private Map<String, HostInformation> hostInformationMap;

  public int getPort() {
    return port;
  }

  public Map<String, HostInformation> getHostInformationMap() {
    return hostInformationMap;
  }
}
