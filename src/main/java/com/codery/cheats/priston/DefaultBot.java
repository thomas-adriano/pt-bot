package com.codery.cheats.priston;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RunnableFuture;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBot implements Bot {

    private String[] stopCmd = Bot.DEFAULT_STOP_CMD;
    private final ExecutorService executorService;
    private final ScreenEventsListener eventsListener;

    public DefaultBot(ExecutorService execService, ScreenEventsListener eventsListener) {
        this.executorService = execService;
        this.eventsListener = eventsListener;
    }

    @Override
    public Bot setStopCommand(String key, String... cmd) {
        List<String> command = new ArrayList<>();
        command.add(key);
        if (cmd != null) {
            for (String each : cmd) {
                command.add(each);
            }
        }

        if (!command.isEmpty()) {
            this.stopCmd = command.toArray(new String[command.size()]);
        }

        return this;
    }

    String[] getStopCommand() {
        return stopCmd;
    }


    @Override
    public ExecutionPromise run(Script s) {
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

                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        executorService.submit(stopListener);
        
        return new DefaultExecutionPromise(s, stopCmd);
    }


    @Override
    public Script createScript() {
        SmarterRobot r = null;
        try {
            r = new SmarterRobot();
        } catch (AWTException e) {
            throw new RuntimeException("It was not possible to create an isntace of " + Robot.class.getSimpleName() + ". " + e.getMessage());
        }
        return new DefaultScript(r);
    }

    @Override
    public void terminateBot() {
        executorService.shutdownNow();
        if (executorService.isShutdown()) {
            System.exit(-1);
        }
    }

    private static class DefaultExecutionPromise implements ExecutionPromise {

        private final Script script;
        private final String[] stopCmd;

        public DefaultExecutionPromise(Script s, String... stopCmd) {
            this.script = s;
            this.stopCmd = stopCmd;
        }

        @Override
        public void once() {
            script.printSummary();
            script.execute();
        }

        @Override
        public void forever() {
            script.printSummary();
            while (true) {
                script.execute();
            }
        }

        @Override
        public void loop(int times) {
            script.printSummary();
            for (int i = 0; i < times; i++) {
                script.execute();
            }
        }
    }

}
