package com.codery.cheats.priston;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/**
 * Created by thomasadriano on 25/09/15.
 */
public class StopBotCmdListener implements CmdListener {

	private final ExecutorService executor;
	private final ScreenEventsListener eventsListener;
	private final Integer[] stopCmd;
	private boolean stillRunning = true;

	public <T extends ScreenEventsListener> StopBotCmdListener(ExecutorService executor, T eventsListener,
			Integer[] stopCmd) {
		this.executor = executor;
		this.eventsListener = eventsListener;
		this.stopCmd = stopCmd;
	}

	@Override
	public void start() {

		Runnable stopListener = new Runnable() {
			@Override
			public void run() {
				ScreenEventsListener.KeyListener l = new GlobalScreenEventsListener.GlobalKeyListener();
				Set<Integer> keysPressed = new HashSet<>();

				l.keyPressed((k) -> {
					// 162 pressed! ¢
					// 164 pressed! ¤
					// 88 pressed! X

					for (int i = 0; i < stopCmd.length; i++) {
						if (k.asciiCode() == stopCmd[i]) {
							keysPressed.add(stopCmd[i]);
						}
					}

					if (keysPressed.size() == stopCmd.length) {
						System.out.println("Stop command " + Arrays.toString(stopCmd) + " pressed.");
						terminateBot();
					}

				});

				eventsListener.addKeyListener(l);
				eventsListener.listen();

				while (stillRunning) {
					sleep(100);
				}
			}
		};

		executor.submit(stopListener);
	}

	@Override
	public void stop() {
		stillRunning = false;
	}

	public void terminateBot() {
		executor.shutdownNow();
		while (!executor.isShutdown()) {
			sleep(100);
		}
	
		System.exit(-1);
	}

	private void sleep(long millis) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException("There was a problem trying to sleep here, bro.");
		}

	}
}
