package com.codery.cheats.priston;

/**
 * Created by thomasadriano on 07/10/15.
 */
public interface Application {

    Application registerStrategies(App.BotStrategy strategy, App.BotStrategy... s);

    Application registerListeners(CmdListener listener, CmdListener... l);

    void runStrategy(App.BotStrategy strategy);

    void start();

}
