package com.lenz.stock.manager.conditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Michael Lenz
 *
 */
public class OsLinux implements OsInterface {

  @Autowired
  private ApplicationContext applicationContext;

  /*
   * (non-Javadoc)
   * @see com.lenz.stock.manager.conditional.OsInterface#getName()
   */
  @Override
  public String getName() {
    return "Runing on: " + applicationContext.getEnvironment().getProperty("os.name");
  }
}
