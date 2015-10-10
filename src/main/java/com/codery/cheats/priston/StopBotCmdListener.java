package com.codery.cheats.priston;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 25/09/15.
 */
public class StopBotCmdListener extends AbstractCmdListener {

    private final Integer[] stopCmd;

    public <T extends ScreenEventsListener> StopBotCmdListener(ExecutorService executor, T eventsListener,
                                                               Integer[] stopCmd) {
        super(executor, eventsListener);
        this.stopCmd = stopCmd;
    }

    @Override
    public void start() {

        Runnable stopListener = () -> {
            keyListener.keyPressed((k) -> {
                Object lock = new Object();
                synchronized (lock) {
                    resetKeysPressed(k);

                    for (int i = 0; i < stopCmd.length; i++) {
                        if (k.asciiCode() == stopCmd[i]) {
                            keysPressed.add(stopCmd[i]);
                        }
                    }

                    if (keysPressed.size() == stopCmd.length) {
                        System.out.println("Stop command " + Arrays.toString(stopCmd) + " pressed.");
                        terminateBot();
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

    public void terminateBot() {
        executor.shutdownNow();
        while (!executor.isShutdown()) {
            sleep(100);
        }

        System.exit(-1);
    }

    private void sleep(long millis) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("There was a problem trying to sleep here, bro.");
        }

    }
}
