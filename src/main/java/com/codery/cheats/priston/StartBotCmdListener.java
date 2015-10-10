package com.codery.cheats.priston;

import java.util.*;
import java.util.concurrent.ExecutorService;

public class StartBotCmdListener extends AbstractCmdListener {

	private final Application app;

	private final Map<Set<Integer>, App.BotStrategy> strategies;

	public <T extends ScreenEventsListener> StartBotCmdListener(ExecutorService executor, T eventsListener,
			Application app, Map<Set<Integer>, App.BotStrategy> strategies) {
		super(executor, eventsListener);
		this.app = app;
		this.strategies = strategies;
	}

	@Override
	public void start() {

		Runnable startListener = () -> {

			keyListener.keyPressed((k) -> {

				resetKeysPressed(k);

				for (Set<Integer> cmd : strategies.keySet()) {
					for (Integer i : cmd) {
						if (k.asciiCode() == i) {
							if (!keysPressed.contains(i)) {
								keysPressed.add(i);
							}
						}

					}
				}

				if (strategies.containsKey(keysPressed)) {
					System.out.print("");
					startBot();
				}

			});

			eventsListener.addKeyListener(keyListener);
			eventsListener.listen();

			while (running) {
				sleep(100);
			}
		};

		executor.submit(startListener);
	}

	@Override
	public void stop() {
		running = false;
	}

	public void startBot() {
		if (!running) {
			runStrategy();
			running = true;
		} else {
			executor.shutdownNow();
			while (!executor.isTerminated()) {sleep(100);}
			running = false;
			runStrategy();
		}
	}

	private void runStrategy() {
		Runnable r = () -> {
			app.runStrategy(strategies.get(keysPressed));
		};
		
		executor.submit(r);	
	}
	
	private void sleep(long millis) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException("There was a problem trying to sleep here, bro.");
		}

	}
}
