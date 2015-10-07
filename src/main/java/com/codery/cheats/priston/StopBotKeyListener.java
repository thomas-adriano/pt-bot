package com.codery.cheats.priston;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * Created by thomasadriano on 25/09/15.
 */
public class StopBotKeyListener {
//
//    private final String[] stopCmd;
//    private final Bot bot;
//
//    public StopBotKeyListener(Bot bot, String[] stopCmd) {
//        this.stopCmd = stopCmd;
//        this.bot = bot;
//    }
//
//    @Override
//    public void keyPressed(KeyEvent event) {
//        verifyKeys(event);
//    }
//
//    @Override
//    public void keyReleased(KeyEvent event) {
//        verifyKeys(event);
//    }
//
//    private void verifyKeys(KeyEvent event) {
//        System.out.println(event);
//        Set<String> expectedCmd = Arrays.asList(stopCmd).stream().map(String::toLowerCase).collect(Collectors.toSet());
//        Set<String> actualCmd = new HashSet<>();
//
//        if (event.isAltPressed()) {
//            actualCmd.add("alt");
//        }
//
//        if (event.isCtrlPressed()) {
//            actualCmd.add("ctrl");
//        }
//
//        if (event.isShiftPressed()) {
//            actualCmd.add("shift");
//        }
//
//        if (!expectedCmd.containsAll(actualCmd)) {
//            return;
//        }
//
//        Set<String> cmdWithoutAuxKeys = new HashSet<>(expectedCmd);
//        cmdWithoutAuxKeys.removeAll(Arrays.asList("ctrl", "alt", "shift"));
//
//        for (String cmd : cmdWithoutAuxKeys) {
//            int keyCode = event.getVirtualKeyCode();
//            int actualKey = (int) cmd.charAt(0);
//            System.out.println("Comparing " + keyCode + " with " + actualKey);
//            if (keyCode == actualKey || keyCode == actualKey - 32) {
//                System.out.println("Stop command " + Arrays.toString(stopCmd) + " pressed.");
//                bot.terminateBot();
//            }
//        }
//    }

}
