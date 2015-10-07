package com.codery.cheats.priston;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBotFactory {

    public static DefaultBot newInstance() {
        return new DefaultBot(Executors.newFixedThreadPool(4), new GlobalScreenEventsListener());
    }
}
