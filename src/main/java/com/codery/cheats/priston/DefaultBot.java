package com.codery.cheats.priston;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBot implements Bot {

    private String[] stopCmd = Bot.DEFAULT_STOP_CMD;
    private ExecutorService executorService;

    public DefaultBot(ExecutorService execService) {
        this.executorService = execService;
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
