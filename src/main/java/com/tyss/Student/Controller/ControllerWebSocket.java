package com.tyss.Student.Controller;

import com.tyss.Student.entity.Greeting;
import com.tyss.Student.entity.Hello;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class ControllerWebSocket {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greet(Hello message)
  {
    return  new Greeting("Hello, "+ HtmlUtils.htmlEscape(message.getName()));
  }
}
