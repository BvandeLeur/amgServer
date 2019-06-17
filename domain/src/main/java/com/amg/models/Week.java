package com.amg.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Week {
    @Getter
    List<Task> taskList;

    public Week(ArrayList<Task> list) {
        taskList = list;
    }
}
