package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface Bot {

    ExecutionPromise run(Script s);

    Script createScript();

}
