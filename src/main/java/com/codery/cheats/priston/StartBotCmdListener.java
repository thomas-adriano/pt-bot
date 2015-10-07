package com.codery.cheats.priston;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartBotCmdListener implements CmdListener {
	private final ExecutorService executor;
	private final ScreenEventsListener eventsListener;
	private final int[][] stopCmd;
	private final Bot bot;
	private boolean stillRunning;
	private final Set<Script> scripts;
	private Set<Integer> keysPressed = new HashSet<>();

	public <T extends ScreenEventsListener> StartBotCmdListener(ExecutorService executor, T eventsListener,
			int[][] stopCmd, Bot bot, Collection<Script> script) {
		this.executor = executor;
		this.eventsListener = eventsListener;
		this.stopCmd = stopCmd;
		this.bot = bot;
		this.scripts = new HashSet<>(script);
		
		if (scripts.size() != stopCmd.length) {
			throw new RuntimeException("You have to add one startCmd for script present.");
		}
	}

	@Override
	public void start() {

		Runnable stopListener = new Runnable() {
			@Override
			public void run() {
				ScreenEventsListener.KeyListener l = new GlobalScreenEventsListener.GlobalKeyListener();

				l.keyPressed((k) -> {
					// 162 pressed! ¢
					// 164 pressed! ¤
					// 88 pressed! X

					for (int i = 0; i < stopCmd.length; i++) {
						for (int j = 0; j < stopCmd[i].length; i++) {
							if (k.asciiCode() == stopCmd[i][j]) {
								keysPressed.add(stopCmd[i][j]);
							}
						}
					}

					if (keysPressed.size() == stopCmd.length) {
							System.out.println("Pause command " + Arrays.toString(stopCmd) + " pressed.");
						
						
						startBot();
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

	public void startBot() {
		// TODO TERMINAR
		for (int i = 0; i < keysPressed.size(); i++) {
			bot.run(new ArrayList<>(scripts).get(i));
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
