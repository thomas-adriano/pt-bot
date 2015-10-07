package com.codery.cheats.priston;

import org.junit.Test;

/**
 * Created by thomasadriano on 06/10/15.
 */
public class GlobalScreenEventsListenerTest {

    @Test
    public void t() throws InterruptedException {
        GlobalScreenEventsListener l = new GlobalScreenEventsListener();

        GlobalScreenEventsListener.GlobalKeyListener lis = new GlobalScreenEventsListener.GlobalKeyListener();
        lis.keyPressed((k) -> System.out.println(k.asciiCode()+ " pressed!"));

        l.addKeyListener(lis);

        l.listen();

        while (true) {
            Thread.sleep(100);
        }

    }
}