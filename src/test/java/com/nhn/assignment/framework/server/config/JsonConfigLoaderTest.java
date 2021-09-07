package com.nhn.assignment.framework.server.config;

import org.junit.Assert;
import org.junit.Test;

public class JsonConfigLoaderTest {

  private JsonConfigLoader sut = new JsonConfigLoader();

  @Test
  public void loadConfigTest() {
    //given
    String testConfigLocation = "test.json";

    //when
    Config actual = sut.load(testConfigLocation);

    //then
    Assert.assertEquals(9999, actual.getPort());
    Assert.assertEquals("/Users/songjuyong/workspace/nhn-assignment/docBase/localhost",
        actual.getHostInformationMap().get("localhost").getDocBase());
    Assert.assertEquals("com.nhn.assignment.application",
        actual.getHostInformationMap().get("localhost").getApplicationRootPackage());
  }
}