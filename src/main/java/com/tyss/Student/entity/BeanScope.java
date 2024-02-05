package com.tyss.Student.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS) or
//@RequestScope
//@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
//@SessionScope
//@ApplicationScope
@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BeanScope {

  public BeanScope() {
    System.out.println("WebSocket Scope");
  }

  private String msg;
}
