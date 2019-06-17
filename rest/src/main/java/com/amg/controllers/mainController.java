package com.amg.controllers;

import com.amg.models.Week;
import com.amg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class mainController {

    final
    GameService game;

    @Autowired
    public mainController(GameService game) {
        this.game = game;
    }

    @GetMapping("/start")
    public void startGame() {
        game.start();
    }

    @GetMapping("/tasks")
    public Week getTasks(){
        return game.getWeeks().get(0);
    }
}
