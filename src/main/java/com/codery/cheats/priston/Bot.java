package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface Bot {

    static final String[] DEFAULT_STOP_CMD = {"ctrl", "alt", "x"};

    Bot setStopCommand(String key, String... cmd);

    ExecutionPromise run(Script s);

    Script createScript();

}
