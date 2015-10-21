package com.codery.cheats.priston;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 08/10/15.
 */
public abstract class AbstractCmdListener implements CmdListener {


    protected boolean running = false;
    protected final ExecutorService executor;
    protected final ScreenEventsListener eventsListener;
    private final long[] lastExecutionTime = new long[1];
    protected List<Integer> keysPressed = new ArrayList<>();
    protected ScreenEventsListener.KeyListener keyListener = new GlobalScreenEventsListener.GlobalKeyListener();

    public AbstractCmdListener(ExecutorService executor, ScreenEventsListener eventsListener) {
        this.executor = executor;
        this.eventsListener = eventsListener;
    }

    protected void resetKeysPressed(ScreenEventsListener.KeyEvent k) {
        long timeElapsed = -1;

        if (lastExecutionTime[0] != 0) {
            timeElapsed = k.eventTime() - lastExecutionTime[0];
        }

        lastExecutionTime[0] = k.eventTime();

        System.out.println("Time elapsed since last keypress: " + timeElapsed);

        if (timeElapsed > 300l && timeElapsed != -1) {
            keysPressed.clear();
        }
    }


}
