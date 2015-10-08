package com.codery.cheats.priston;

import javaslang.collection.List;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thomasadriano on 07/10/15.
 */
public class DefaultApplication implements Application {

    private final Set<App.BotStrategy> strategies = new HashSet<>();
    private final Set<CmdListener> cmdListeners = new HashSet<>();

    @Override
    public Application registerStrategies(App.BotStrategy strategy, App.BotStrategy... s) {
        strategies.addAll(List.of(s).append(strategy).toJavaList());
        return this;
    }

    @Override
    public Application registerListeners(CmdListener listener, CmdListener... l) {
        cmdListeners.addAll(List.of(l).append(listener).toJavaList());
        return this;
    }

    @Override
    public void runStrategy(App.BotStrategy strategy) {
        strategies.stream().filter((st) -> st == strategy).findFirst().ifPresent((st) -> st.run());
    }

    @Override
    public void start() {
        for (CmdListener listener : cmdListeners) {
        	System.out.println("Stargint listener "+listener);
            listener.start();
        }

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
