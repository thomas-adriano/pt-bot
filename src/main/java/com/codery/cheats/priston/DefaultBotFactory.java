package com.codery.cheats.priston;

import javaslang.collection.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBotFactory {

    private final static int[] DEFAULT_STOP_CMD = new int[]{162/*ctrl*/, 164/*alt*/, 88/*x*/};

    public static DefaultBot newInstance() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        return new DefaultBot(List.of(new StopBotCmdListener(executor, new GlobalScreenEventsListener(), DEFAULT_STOP_CMD)).toJavaList());
    }

}
