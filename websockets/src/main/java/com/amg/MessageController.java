package com.amg;

import com.amg.models.GreetingModel;
import com.amg.models.socketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingModel greeting(socketMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GreetingModel("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}