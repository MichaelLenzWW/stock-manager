package com.lenz.stock.manager.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {

  /*
   * (non-Javadoc)
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
   */
  @Override
  public void onApplicationEvent(final ContextStartedEvent event) {
    System.out.println("******************************************************************************************");
    System.out.println("ContextStartedEvent Received: " + event);
    System.out.println("******************************************************************************************");
  }
}