package com.codery.cheats.priston;

import java.util.List;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface Script {

    Script add(GameAction act);

    void execute();

    void printSummary();
}
