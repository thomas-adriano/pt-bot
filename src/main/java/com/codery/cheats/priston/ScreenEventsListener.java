package com.codery.cheats.priston;

import java.util.function.Consumer;

/**
 * Created by thomasadriano on 06/10/15.
 */
public interface ScreenEventsListener extends AutoCloseable {

    <T extends KeyListener> ScreenEventsListener addKeyListener(T listener);

    void listen();

    void stopListening() throws Exception;

    public static interface KeyListener {
        void keyPressed(Consumer<KeyEvent> cons);

        void keyReleased(Consumer<KeyEvent> cons);

        Consumer<KeyEvent> getKeyPressedConsumer();

        Consumer<KeyEvent> getKeyReleasedConsumer();
    }

    public static interface KeyEvent {

        int asciiCode();

    }
}
