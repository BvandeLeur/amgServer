package com.amg.component;

import com.amg.models.Player;
import com.amg.models.Task;
import com.amg.models.Week;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeekGenerator {

    private static Random rnd = new Random();

    private static int calculateMax(int size, int NORMAL_WEEK, int BURNOUT) {
        return size * (NORMAL_WEEK + BURNOUT) - 5;
    }

    public static Week generateWeek(List<Player> players, int NORMAL_WEEK, int BURNOUT) {
        int max = calculateMax(players.size(), NORMAL_WEEK, BURNOUT);
        boolean creating = true;
        ArrayList<Task> res = new ArrayList<Task>();

        while(creating){
            res.add(new Task(res.size() + 1, rnd.nextInt(16) + 1));

            if (totalLength(res) == max)
                creating = false;

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
