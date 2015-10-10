package com.codery.cheats.priston;

import java.util.*;
import java.util.concurrent.ExecutorService;

public class StartBotCmdListener extends AbstractCmdListener {

    private final Application app;
    private final Map<Integer[], App.BotStrategy> strategies;

    public <T extends ScreenEventsListener> StartBotCmdListener(ExecutorService executor, T eventsListener,
                                                                Application app, Map<Integer[], App.BotStrategy> strategies) {
        super(executor, eventsListener);
        this.app = app;
        this.strategies = strategies;
    }

    @Override
    public void start() {

        Runnable stopListener = () -> {

            keyListener.keyPressed((k) -> {
                Object lock = new Object();

                synchronized (lock) {
                    resetKeysPressed(k);

                    for (Integer[] cmd : strategies.keySet()) {
                        for (int i = 0; i < cmd.length; i++) {
                            if (k.asciiCode() == cmd[i]) {
                                if (!keysPressed.contains(cmd[i])) {
                                    keysPressed.add(cmd[i]);
                                }
                            }
                        }
                    }

                    if (strategies.containsKey(keysPressed.toArray(new Integer[keysPressed.size()])) && !running) {
                        System.out.println("Start command " + keysPressed + " pressed.");
                        startBot();
                    }
                }

            });

            eventsListener.addKeyListener(keyListener);
            eventsListener.listen();

            while (running) {
                sleep(100);
            }
        };


        executor.submit(stopListener);
    }

    @Override
    public void stop() {
        running = false;
    }

    public void startBot() {
        running = true;
        app.runStrategy(strategies.get(keysPressed));
    }

    private void sleep(long millis) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("There was a problem trying to sleep here, bro.");
        }

    }
}
