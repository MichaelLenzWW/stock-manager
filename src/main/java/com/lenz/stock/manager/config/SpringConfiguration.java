package com.lenz.stock.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lenz.stock.manager.conditional.LinuxCondition;
import com.lenz.stock.manager.conditional.OsInterface;
import com.lenz.stock.manager.conditional.OsLinux;
import com.lenz.stock.manager.conditional.OsWindows;
import com.lenz.stock.manager.conditional.WindowsCondition;

/**
 * Configuration class for Spring.
 * 
 * @author Michael Lenz
 *
 */
@Configuration
@ComponentScan("com.lenz.stock.manager")
@EnableTransactionManagement
@PropertySource(value = { "classpath:customProperties.properties" }, ignoreResourceNotFound = false)
public class SpringConfiguration {

  // ******************************************************************************************
  // Conditional configuration of the OsInterface
  // ******************************************************************************************
  @Bean
  @Conditional(LinuxCondition.class)
  public OsInterface getLinuxImplementation() {
    System.out.println("Linux Condition");
    return new OsLinux();
  }

  @Bean
  @Conditional(WindowsCondition.class)
  public OsInterface getWindowsImplementation() {
    System.out.println("Windows Condition");
    return new OsWindows();
  }
}
