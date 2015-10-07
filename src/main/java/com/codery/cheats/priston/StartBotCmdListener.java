package com.codery.cheats.priston;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartBotCmdListener implements CmdListener {
    private final ExecutorService executor;
    private final ScreenEventsListener eventsListener;
    private final Application app;
    private final Map<Integer[], App.BotStrategy> strategies;
    private boolean stillRunning;
    private List<Integer> keysPressed = new ArrayList<>();

    public <T extends ScreenEventsListener> StartBotCmdListener(ExecutorService executor, T eventsListener, Application app, Map<Integer[], App.BotStrategy> strategies) {
        this.executor = executor;
        this.eventsListener = eventsListener;
        this.app = app;
        this.strategies = strategies;
    }

    @Override
    public void start() {

        Runnable stopListener = new Runnable() {
            @Override
            public void run() {
                ScreenEventsListener.KeyListener l = new GlobalScreenEventsListener.GlobalKeyListener();

                l.keyPressed((k) -> {
                    // 162 pressed! ¢
                    // 164 pressed! ¤
                    // 88 pressed! X

                    for (Integer[] cmd : strategies.keySet()) {
                        for (int i = 0; i < cmd.length; i++) {
                            if (k.asciiCode() == cmd[i]) {
                                keysPressed.add(cmd[i]);
                            }
                        }
                    }

                    System.out.println("Stop command " + keysPressed + " pressed.");
                    startBot();

                });

                eventsListener.addKeyListener(l);
                eventsListener.listen();

                while (stillRunning) {
                    sleep(100);
                }
            }
        };

        executor.submit(stopListener);
    }

    @Override
    public void stop() {
        stillRunning = false;
    }

    public void startBot() {
        // TODO TERMINAR
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
