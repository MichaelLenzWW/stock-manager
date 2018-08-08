package com.lenz.stock.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
}
