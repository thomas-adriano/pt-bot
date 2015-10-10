package com.codery.cheats.priston;

import java.util.Set;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface GameAction {

    void execute(SmarterRobot r);

    GameAction times(int times);

    GameAction interval(long millis);

    GameAction schedule(long millis);

    GameAction after(Actions act, int times);

    int getTimes();

    long getInterval();

    long getSchedule();

    long getLastExecution();

    Set<? extends Constraint> getConstraints();

    interface Constraint {
        Actions getAction();

        int getTimes();

        boolean fulfills(int actualTimesExecuted);
    }

}
