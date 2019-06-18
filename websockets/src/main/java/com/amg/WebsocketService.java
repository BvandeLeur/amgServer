package com.amg;

import com.amg.models.JoinGameMessage;
import com.amg.models.WeekMessage;
import com.amg.models.socketMessage;
import com.amg.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketService {

    GameService gameService = new GameService();

    @MessageMapping("/EindeWeek")
    @SendTo("/topic/ServerMessages")
    public WeekMessage EindeWeek() throws Exception {
        gameService.doneWithWeek();
        WeekMessage weekMessage = new WeekMessage(gameService.getWeek());
        return weekMessage;
    }

    @MessageMapping("/StartGame")
    @SendTo("/topic/ServerMessages")
    public WeekMessage joiner() throws Exception {
        gameService.start();

        WeekMessage weekMessage = new WeekMessage(gameService.getWeek());
        return weekMessage;
    }
}