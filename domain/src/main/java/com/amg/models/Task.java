package com.amg.models;

import lombok.Getter;
import lombok.Setter;

public class Task {

    //  hoe lang het duurt
    @Getter
    int hour;

    //  wie het gaat doen
    @Getter
    @Setter
    Player player;

    public Task(int hour) {
        this.hour = hour;
    }
}
