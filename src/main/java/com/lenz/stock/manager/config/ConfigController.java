package com.lenz.stock.manager.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Lenz
 *
 */
@RestController
public class ConfigController {

  private static final String PATH = "/config";

  /** Existing property from application.properties value. */
  @Value("${server.port}")
  String existingProperty;

  /** Not existing value with default value. */
  @Value("${mischnixx:0815}")
  String notExistingPropertyWithDefault;

  /** Existing property from customProperties.properties value. */
  @Value("${customProperty01}")
  String customProperty;

  /**
   * 
   * @param request
   * @param response
   * @return
   */
  @GetMapping(PATH)
  public List<String> getConfiguration(final HttpServletRequest request, final HttpServletResponse response) {

    List<String> configurationValues = new ArrayList<>();
    configurationValues.add("Server port: " + existingProperty);
    configurationValues.add("Mischnixx: " + notExistingPropertyWithDefault);
    configurationValues.add("Custom Property 01: " + customProperty);

    return configurationValues;
  }
}
