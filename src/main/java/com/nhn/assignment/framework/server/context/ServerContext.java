package com.nhn.assignment.framework.server.context;

import com.nhn.assignment.framework.server.config.Config;
import com.nhn.assignment.framework.server.config.ConfigLoader;
import com.nhn.assignment.framework.server.config.HostInformation;
import com.nhn.assignment.framework.server.config.JsonConfigLoader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServerContext implements Context {

  private final String configLocation;

  private Config config;
  private List<ApplicationContext> applicationContexts;

  public ServerContext(String configLocation) {
    this.configLocation = configLocation;
  }

  @Override
  public void init() {
    this.config = loadConfig(configLocation);
    this.applicationContexts = loadApplicationContext(this.config.getHostInformationMap());
  }

  private Config loadConfig(String configLocation) {
    ConfigLoader configLoader = new JsonConfigLoader();

    return configLoader.load(configLocation);
  }

  private List<ApplicationContext> loadApplicationContext(
      Map<String, HostInformation> hostInformationMap) {

    List<ApplicationContext> applicationContexts = hostInformationMap
        .keySet()
        .stream()
        .map(host -> new HostBasedApplicationContext(host, hostInformationMap.get(host)))
        .collect(Collectors.toList());

    applicationContexts.forEach(Context::init);

    return applicationContexts;
  }

  public Config getConfig() {
    return config;
  }

  public List<ApplicationContext> getApplicationContexts() {
    return applicationContexts;
  }
}
