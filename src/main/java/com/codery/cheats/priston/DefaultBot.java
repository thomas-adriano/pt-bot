package com.codery.cheats.priston;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBot <T extends CmdListener> implements Bot {

    private String[] stopCmd = Bot.DEFAULT_STOP_CMD;
    private final Set<T> cmdListeners;


    public DefaultBot(List<T> eventsListeners) {
        this.cmdListeners = new HashSet<>(eventsListeners);
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
        return new DefaultExecutionPromise(s, cmdListeners);
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

    private static class DefaultExecutionPromise <T extends CmdListener> implements ExecutionPromise {

        private final Script script;
        private final Set<T> cmdListeners;

        public DefaultExecutionPromise(Script s, Set<T> cmdListeners) {
            this.script = s;
            this.cmdListeners = cmdListeners;
        }

        @Override
        public void once() {
            script.printSummary();
            startListeners();
            script.execute();
        }

        @Override
        public void forever() {
            script.printSummary();
            startListeners();
            while (true) {
                script.execute();
            }
        }

        @Override
        public void loop(int times) {
            script.printSummary();
            startListeners();
            for (int i = 0; i < times; i++) {
                script.execute();
            }
        }

        private void startListeners() {
            for (CmdListener listener : cmdListeners) {
                listener.start();
            }
        }

    }

}
