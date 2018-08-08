package com.lenz.stock.manager.conditional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Lenz
 *
 */
@RestController
public class OsController {

  private static final String PATH = "/os";

  @Autowired
  private OsInterface operationgSystem;

  /**
   * 
   * @param request
   * @param response
   * @return
   */
  @GetMapping(PATH)
  public String getOs(final HttpServletRequest request, final HttpServletResponse response) {
    return operationgSystem.getName();
  }
}
