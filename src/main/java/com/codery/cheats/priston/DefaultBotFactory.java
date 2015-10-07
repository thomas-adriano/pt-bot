package com.codery.cheats.priston;

import javaslang.collection.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBotFactory {

    private final static int[] DEFAULT_STOP_CMD = new int[]{162/*ctrl*/, 164/*alt*/, 88/*x*/};
    private final static int[] DEFAULT_PAUSE_CMD = new int[]{162/*ctrl*/, 164/*alt*/, 90/*z*/};

    public static DefaultBot newInstance() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        GlobalScreenEventsListener eventsListener = new GlobalScreenEventsListener();

        return new DefaultBot(List.of(
        		new StopBotCmdListener(executor, eventsListener, DEFAULT_STOP_CMD)
//        		new StartBotCmdListener(executor, eventsListener, DEFAULT_PAUSE_CMD)
        		).toJavaList());
    }

}
