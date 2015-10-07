package com.codery.cheats.priston;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 25/09/15.
 */
public class StopBotCmdListener implements CmdListener{

    private final ExecutorService executor;
    private final ScreenEventsListener eventsListener;
    private final int[] stopCmd;
    private boolean stillRunning = true;

    public <T extends ScreenEventsListener> StopBotCmdListener(ExecutorService executor, T eventsListener, int[] stopCmd) {
        this.executor = executor;
        this.eventsListener = eventsListener;
        this.stopCmd = stopCmd;
    }

    @Override
    public void start() {

        Runnable stopListener = new Runnable() {
            @Override
            public void run() {
                ScreenEventsListener.KeyListener l = new GlobalScreenEventsListener.GlobalKeyListener();
                Set<Integer> keysPressed = new HashSet<>();
                int[] stopCmd2 = new int[]{162, 164, 88};

                l.keyPressed((k) -> {
//            162 pressed! ¢
//            164 pressed! ¤
//            88 pressed! X

                    for (int i = 0; i < stopCmd.length; i++) {
                        if (k.asciiCode() == stopCmd2[i]) {
                            keysPressed.add(stopCmd2[i]);
                        }
                    }

                    if (keysPressed.size() == stopCmd.length) {
                        System.out.println("Stop command " + Arrays.toString(stopCmd) + " pressed.");
                        terminateBot();
                    }

                });

                eventsListener.addKeyListener(l);
                eventsListener.listen();

                while (stillRunning) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        executor.submit(stopListener);
    }

    @Override
    public void stop() {
        stillRunning = false;
    }

    public void terminateBot() {
        executor.shutdownNow();
        while (!executor.isShutdown()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException("There was a problem trying to sleep here, bro.");
            }
        }
        System.exit(-1);
    }
}
