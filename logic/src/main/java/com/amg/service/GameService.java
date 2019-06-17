package com.amg.service;

import com.amg.component.WeekGenerator;
import com.amg.models.Week;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private static final int AMMOUNT_OF_WEEKS = 1;

    @Getter
    List<Week> weeks;

    public void start(){
        weeks = new ArrayList<Week>();
        for (int i = 0; i < AMMOUNT_OF_WEEKS; i++) {
            weeks.add(WeekGenerator.generateWeek(65));
        }
    }
}
