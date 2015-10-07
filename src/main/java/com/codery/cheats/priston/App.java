package com.codery.cheats.priston;


import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class App {

    private static final BotStrategy WALKING_LEVELING = new BotStrategy() {
        @Override
        public void run() {
            Bot b = DefaultBotFactory.newInstance();
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
            Bot b = DefaultBotFactory.newInstance();
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
            Bot b = DefaultBotFactory.newInstance();
            Script s = b.createScript();

            //TODO the use of interval its being repetitive. Put the minumum
            //amount of time to use a potion directly at UsePot class.
            s.add(
                    new CastSpell(Actions._5).interval(200)
            ); //hp pot

            b.run(s).forever();
        }
    };

    public static void main(String[] args) throws InterruptedException, AWTException {
        System.out.println("Starting Priston Bot...");
        Thread.sleep(5000);

        SIMPLE_CLICKING.run();
    }

    private interface BotStrategy {

        void run();

    }
}
