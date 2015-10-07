package com.codery.cheats.priston;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
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
            Script s = b.createScript();
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
            Script s = b.createScript();

            //TODO the use of interval its being repetitive. Put the minumum
            //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions.F5).interval(1300).schedule(301_000))
                    .add(new CastSpell(Actions._5).interval(1000))
                    .add(new UsePotion(1).after(Actions._5, 11)) //res pot
                    .add(new UsePotion(3).after(Actions._5, 16)) //mana pot
                    .add(new UsePotion(2).schedule(24_000)) //hp pot
                    .add(new UsePotion(2).after(Actions.F5, 1)); //hp pot

            b.run(s).forever();
        }
    };

    private static final BotStrategy SIMPLE_CLICKING = new BotStrategy() {
        @Override
        public void run() {
            Bot b = new DefaultBot();
            Script s = b.createScript();

            //TODO the use of interval its being repetitive. Put the minumum
            //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions._5).interval(200)
            ); //hp pot

            b.run(s).forever();
        }
    };

    private final static Integer[] DEFAULT_STOP_CMD = new Integer[]{162/*ctrl*/, 164/*alt*/, 88/*x*/};
    private final static Integer[] DEFAULT_START_CMD_0 = new Integer[]{162/*ctrl*/, 164/*alt*/, 48};
    private final static Integer[] DEFAULT_START_CMD_1 = new Integer[]{162/*ctrl*/, 164/*alt*/, 49};
    private final static Integer[] DEFAULT_START_CMD_2 = new Integer[]{162/*ctrl*/, 164/*alt*/, 50};
    private final static Integer[] DEFAULT_START_CMD_3 = new Integer[]{162/*ctrl*/, 164/*alt*/, 51};
    private final static Integer[] DEFAULT_START_CMD_4 = new Integer[]{162/*ctrl*/, 164/*alt*/, 52};
    private final static Integer[] DEFAULT_START_CMD_5 = new Integer[]{162/*ctrl*/, 164/*alt*/, 53};
    private final static Integer[] DEFAULT_START_CMD_6 = new Integer[]{162/*ctrl*/, 164/*alt*/, 54};
    private final static Integer[] DEFAULT_START_CMD_7 = new Integer[]{162/*ctrl*/, 164/*alt*/, 55};
    private final static Integer[] DEFAULT_START_CMD_8 = new Integer[]{162/*ctrl*/, 164/*alt*/, 56};
    private final static Integer[] DEFAULT_START_CMD_9 = new Integer[]{162/*ctrl*/, 164/*alt*/, 57};

    public static void main(String[] args) throws InterruptedException, AWTException {
        System.out.println("Starting Priston Bot...");
        Thread.sleep(5000);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        GlobalScreenEventsListener eventsListener = new GlobalScreenEventsListener();

        Application app = new DefaultApplication();

        Map<Integer[], BotStrategy> strategies = new HashMap<>();
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
