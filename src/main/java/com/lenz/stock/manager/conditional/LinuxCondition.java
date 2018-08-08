package com.lenz.stock.manager.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
  @Override
  public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
    return context.getEnvironment().getProperty("os.name").contains("Linux");
  }
}