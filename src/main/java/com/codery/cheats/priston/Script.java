package com.codery.cheats.priston;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface Script {

	String getName();
	
    Script add(GameAction act);

    void execute();

    void printSummary();
}
