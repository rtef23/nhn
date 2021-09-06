package com.nhn.assignment.framework.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class JsonConfigLoader implements ConfigLoader {

  @Override
  public Config load(String configLocation) {
    try (
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(configLocation)))
        )) {
      ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.readValue(bufferedReader, Config.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
