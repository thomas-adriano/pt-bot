package com.codery.cheats.priston;

import java.awt.*;

import java.util.Arrays;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class App {

    private static final BotStrategy WALKING_LEVELING = new BotStrategy() {
        @Override
        public void run() {
            Bot b = new DefaultBot();
            Script s = b.createScript("WALKING_LEVELING");
            Screen screen = new Screen();
//
//        //TODO the use of interval its being repetitive. Put the minumum
//        //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions.F4).interval(2000).schedule(361_000))
                    .add(new CastSpell(Actions.F5).interval(2000).schedule(310_000))
                    .add(new CastSpell(Actions._5).interval(3000))
                    .add(new UsePotion(1).after(Actions._5, 20).interval(1200)) //res pot
                    .add(new UsePotion(3).after(Actions._5, 30).interval(1200)) //mana pot
                    .add(new UsePotion(2).interval(1200).schedule(360_000)) //hp pot
                    .add(new Walk(screen.getYCenteredXMaximumLeftCoordinates(), 2000).after(Actions._5, 10).interval(500))
                    .add(new Walk(screen.getYCenteredXMaximumRightCoordinates(), 2000).after(Actions._5, 12));

            b.run(s).forever();
        }
    };

    private static final BotStrategy STATIC_LEVELING = new BotStrategy() {
        @Override
        public void run() {
            Bot b = new DefaultBot();
            Script s = b.createScript("STATIC_LEVELING");

            //TODO the use of interval its being repetitive. Put the minumum
            //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions.F6).interval(1300).schedule(298_000)) //bubble
                    .add(new CastSpell(Actions.F5).interval(1300).schedule(301_000)) //fairy   
                    .add(new CastSpell(Actions._4).interval(1300).schedule(301_000)) //mana boost
                    .add(new CastSpell(Actions._5).interval(200)) //earthquake
                    .add(new UsePotion(3).interval(10_000)) //mana pot
                    .add(new UsePotion(1).interval(33_000)) //res pot
                    .add(new UsePotion(2).schedule(25_000)); //hp pot

            b.run(s).forever();
        }
    };

    private static final BotStrategy SIMPLE_CLICKING = new BotStrategy() {
        @Override
        public void run() {
            Bot b = new DefaultBot();
            Script s = b.createScript("SIMPLE_CLICKING");

            //TODO the use of interval its being repetitive. Put the minumum
            //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions._5).interval(200)
            ); //hp pot

            b.run(s).forever();
        }
    };

    private final static Set<Integer> DEFAULT_STOP_CMD = new HashSet<>(Arrays.asList(162/*ctrl*/, 88/*x*/));
    private final static Set<Integer> DEFAULT_START_CMD_0 = new HashSet<>(Arrays.asList(162/*ctrl*/, 48));
    private final static Set<Integer> DEFAULT_START_CMD_1 = new HashSet<>(Arrays.asList(162/*ctrl*/, 49));
    private final static Set<Integer> DEFAULT_START_CMD_2 = new HashSet<>(Arrays.asList(162/*ctrl*/, 50));
    private final static Set<Integer> DEFAULT_START_CMD_3 = new HashSet<>(Arrays.asList(162/*ctrl*/, 51));
    private final static Set<Integer> DEFAULT_START_CMD_4 = new HashSet<>(Arrays.asList(162/*ctrl*/, 52));
    private final static Set<Integer> DEFAULT_START_CMD_5 = new HashSet<>(Arrays.asList(162/*ctrl*/, 53));
    private final static Set<Integer> DEFAULT_START_CMD_6 = new HashSet<>(Arrays.asList(162/*ctrl*/, 54));
    private final static Set<Integer> DEFAULT_START_CMD_7 = new HashSet<>(Arrays.asList(162/*ctrl*/, 55));
    private final static Set<Integer> DEFAULT_START_CMD_8 = new HashSet<>(Arrays.asList(162/*ctrl*/, 56));
    private final static Set<Integer> DEFAULT_START_CMD_9 = new HashSet<>(Arrays.asList(162/*ctrl*/, 57));

    private static void printStrategies() {
        for (Field field : App.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException, AWTException {
        System.out.println("Starting Priston Bot...");

        ExecutorService executor = Executors.newCachedThreadPool();

        printStrategies();

        GlobalScreenEventsListener eventsListener = new GlobalScreenEventsListener();

        Application app = new DefaultApplication();

        Map<Set<Integer>, BotStrategy> strategies = new HashMap<>();
        strategies.put(DEFAULT_START_CMD_0, SIMPLE_CLICKING);
        strategies.put(DEFAULT_START_CMD_1, WALKING_LEVELING);
        strategies.put(DEFAULT_START_CMD_2, STATIC_LEVELING);

        CmdListener l1 = new StopBotCmdListener(executor, eventsListener, DEFAULT_STOP_CMD);
        CmdListener l2 = new StartBotCmdListener(executor, eventsListener, app, strategies);


        app.registerListeners(l1, l2);
        app.registerStrategies(WALKING_LEVELING, SIMPLE_CLICKING, STATIC_LEVELING);

        app.start();
    }

    public interface BotStrategy {

        void run();

    }
}
