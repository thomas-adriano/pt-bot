package com.codery.cheats.priston;

import java.util.function.Predicate;

/**
 * Created by thomasadriano on 24/09/15.
 */
public interface ExecutionPromise {

    void once();

    void forever();

    void loop(int times);

//    void loop(Predicate<?> condition);

}
