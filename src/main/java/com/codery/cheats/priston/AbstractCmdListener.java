package com.codery.cheats.priston;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 08/10/15.
 */
public abstract class AbstractCmdListener implements CmdListener {


    protected boolean running = false;
    protected final ExecutorService executor;
    protected final ScreenEventsListener eventsListener;
    private final long[] lastExecutionTime = new long[1];
    protected Set<Integer> keysPressed = new HashSet<>();
    protected ScreenEventsListener.KeyListener keyListener = new GlobalScreenEventsListener.GlobalKeyListener();

    public AbstractCmdListener(ExecutorService executor, ScreenEventsListener eventsListener) {
        this.executor = executor;
        this.eventsListener = eventsListener;
    }

    protected void resetKeysPressed(ScreenEventsListener.KeyEvent k) {
        long timeElapsed = -1;

        if (lastExecutionTime[0] == 0) {
            lastExecutionTime[0] = k.eventTime();
        } else {
            timeElapsed = k.eventTime() - lastExecutionTime[0];
            lastExecutionTime[0] = k.eventTime();
        }

        if (timeElapsed > 1l && timeElapsed != -1) {
            keysPressed.clear();
        }
    }


}
