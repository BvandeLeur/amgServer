package com.amg.service;

import com.amg.component.WeekGenerator;
import com.amg.models.Player;
import com.amg.models.Task;
import com.amg.models.Week;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private static final int AMMOUNT_OF_WEEKS = 10;
    private static final int NORMAL_WEEK = 30;
    private static final int BURNOUT = 10;

    private int currentWeek = 0;
    private int done = 0;

    @Getter
    List<Player> players = new ArrayList<Player>();

    @Getter
    List<Week> weeks = new ArrayList<Week>();

    public void start() {
        for (int i = 0; i < AMMOUNT_OF_WEEKS; i++) {
            weeks.add(WeekGenerator.generateWeek(players, NORMAL_WEEK, BURNOUT));
        }
    }

    public Week getWeek(){
        return weeks.get(currentWeek);
    }

    public void doneWithWeek() {
        done++;
        
        if(done == players.size()){
            nextWeek();
        }
    }

    private void nextWeek() {
        checkForBurnout(NORMAL_WEEK, BURNOUT);

        if(checkForEnd()){
            done = 0;
            currentWeek++;
        //  send message to socket
        }
    }

    public void joinGame(Player p) {
        players.add(p);
    }

    void leaveGame(Player p) {
        players.remove(p);
    }

    void claimTask(Player p, Task t) {
        if (t.getPlayer() == null) t.setPlayer(p);
    }

    List<Task> getTasks() {
        return weeks.get(currentWeek).getTaskList();
    }

    /**
     * checks if user has 10 to many hours in last 2 weeks
     *
     * @param max
     * @param burnout
     */
    private void checkForBurnout(int max, int burnout) {
        int hours;
        for (Player p : players)
            if (!p.isBurnout()) {
                hours = 0;
                final List<Task> tasks1 = weeks.get(currentWeek).getTaskList();
                final List<Task> tasks2 = weeks.get(currentWeek - 1).getTaskList();
                List<Task> total = new ArrayList<Task>() {
                    {
                        addAll(tasks1);
                        addAll(tasks2);
                    }
                };
                for (Task t : total) {
                    if (t.getPlayer() == p)
                        hours += t.getHour();
                }
                if (hours >= (max * 2) + burnout)
                    p.setBurnout(true);
            }
    }

    /**
     * checks if everyone had burnout
     * checks if currentweek == ammount_of_weeks
     */
    private boolean checkForEnd() {
        //  burned out check
        int burnedOut = 0;
        for (Player p : players)
            if(p.isBurnout())
                burnedOut++;
        if (burnedOut >= players.size() - 1){
            return true;
        }

        //  end of game check
        return currentWeek == weeks.size();
    }
}
