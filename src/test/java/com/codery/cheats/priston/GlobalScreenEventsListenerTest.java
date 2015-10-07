package com.codery.cheats.priston;

import org.jnativehook.GlobalScreen;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thomasadriano on 06/10/15.
 */
public class GlobalScreenEventsListenerTest {

	@Test
	public void t() throws Exception {

		GlobalScreenEventsListener l = new GlobalScreenEventsListener();

		GlobalScreenEventsListener.GlobalKeyListener lis = new GlobalScreenEventsListener.GlobalKeyListener();
		lis.keyPressed((k) -> System.out.println(k.asciiCode() + " pressed! "+ k.character()));

		l.addKeyListener(lis);

		l.listen();

//		l.stopListening();
		
		while (true) {
			Thread.sleep(100);
		}


	}
}