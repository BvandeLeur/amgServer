package com.amg.component;

import com.amg.models.Task;
import com.amg.models.Week;

import java.util.ArrayList;
import java.util.Random;

public class WeekGenerator {
    static Random rnd = new Random();

    public static Week generateWeek(int max) {

        ArrayList<Task> res = new ArrayList<Task>();
        while(true){

            res.add(new Task(rnd.nextInt(16) + 1));

            if (totalLength(res) == max)
                break;

            if (totalLength(res) > max)
                res.remove(res.size()-1);

        }
        return new Week(res);
    }

    private static int totalLength(ArrayList<Task> res) {
        int total = 0;
        for (Task t: res) {
            total += t.getHour();
        }
        return total;
    }
}
