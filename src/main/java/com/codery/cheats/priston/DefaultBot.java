package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultBot implements Bot {


    @Override
    public ExecutionPromise run(Script s) {
        return new DefaultExecutionPromise(s);
    }


    @Override
    public Script createScript(String scriptName) {
        SmarterRobot r = null;
        try {
            r = new SmarterRobot();
        } catch (AWTException e) {
            throw new RuntimeException("It was not possible to create an isntace of " + Robot.class.getSimpleName() + ". " + e.getMessage());
        }
        return new DefaultScript(r, scriptName);
    }

    private static class DefaultExecutionPromise implements ExecutionPromise {

        private final Script script;
        boolean once;
        boolean forever;
        boolean loop;

        public DefaultExecutionPromise(Script s) {
            this.script = s;
        }

        @Override
        public void once() {
            once = true;
            execute(0);
        }

        @Override
        public void forever() {
            forever = true;
            execute(0);
        }

        @Override
        public void loop(int times) {
            loop = true;
            execute(times);
        }

        void execute(long times) {
            script.printSummary();
            if (once) {
                script.execute();
            }
            if (forever) {
                while (true) {
                    script.execute();
                }
            }
            if (loop) {
                for (int i = 0; i < times; i++) {
                    script.execute();
                }
            }
        }

    }

}
