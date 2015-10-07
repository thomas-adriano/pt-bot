package com.codery.cheats.priston;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.function.Consumer;

/**
 * Created by thomasadriano on 06/10/15.
 */
public class GlobalScreenEventsListener implements ScreenEventsListener {

    @Override
    public <T extends KeyListener> ScreenEventsListener addKeyListener(T listener) {
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(listener).getNativeKeyListener());
        return this;
    }

    @Override
    public void listen() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException("It wasn't possible to start key listening.\n" + e.getMessage());
        }
    }

    @Override
    public void stopListening() throws Exception {
        close();
    }

    @Override
    public void close() throws Exception {
        GlobalScreen.unregisterNativeHook();
    }

    public static class GlobalKeyEvent implements KeyEvent {

        private final NativeKeyEvent event;

        public GlobalKeyEvent(NativeKeyEvent event) {
            this.event = event;
        }

        @Override
        public int asciiCode() {
            return event.getRawCode();
        }
    }


    public static class GlobalKeyListener implements KeyListener {

        private Consumer<KeyEvent> keyPressedCons;
        private Consumer<KeyEvent> keyReleasedCons;
        private KeyListener listener;

        public GlobalKeyListener(KeyListener listener) {
            this.listener = listener;
            this.keyPressedCons = listener.getKeyPressedConsumer();
            this.keyReleasedCons = listener.getKeyReleasedConsumer();
        }

        public GlobalKeyListener() {

        }

        @Override
        public void keyPressed(Consumer<KeyEvent> cons) {
            this.keyPressedCons = cons;
        }

        @Override
        public void keyReleased(Consumer<KeyEvent> cons) {
            this.keyReleasedCons = cons;
        }

        @Override
        public Consumer<KeyEvent> getKeyPressedConsumer() {
            return keyPressedCons;
        }

        @Override
        public Consumer<KeyEvent> getKeyReleasedConsumer() {
            return keyReleasedCons;
        }

        public NativeKeyListener getNativeKeyListener() {
            return new NativeKeyListener() {
                @Override
                public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                    if (keyPressedCons != null)
                        keyPressedCons.accept(new GlobalKeyEvent(nativeKeyEvent));
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                    if (keyReleasedCons != null)
                        keyReleasedCons.accept(new GlobalKeyEvent(nativeKeyEvent));
                }

                @Override
                public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
                    System.err.println("Operation not supported.");
                }
            };
        }
    }

}
