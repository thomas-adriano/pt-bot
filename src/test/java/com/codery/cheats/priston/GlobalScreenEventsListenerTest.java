package com.codery.cheats.priston;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
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

//		keyListener.stopListening();
		
		while (true) {
			Thread.sleep(100);
		}


	}
	
	@Test
	public void testBasic() throws InterruptedException, NativeHookException {
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);
		
		GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
			
			@Override
			public void nativeKeyTyped(NativeKeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void nativeKeyReleased(NativeKeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {
				System.out.println(e.getWhen());
				
			}
		});
		
		GlobalScreen.registerNativeHook();
		
		while (true) {
			Thread.sleep(100);
		}
	}
}