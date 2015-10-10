package com.codery.cheats.priston;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by thomasadriano on 28/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BotIntegrationTest {

    @Mock
    private SmarterRobot robot;

    @Test
    public void integrationTest() {
        System.out.println("Starting Priston Bot...");

        Bot b = new DefaultBot();
        Script s = new DefaultScript(robot, "testScript");

//        s.add(
//                new CastSpell(Actions.F4).interval(2000).schedule(361000))
//                .add(new CastSpell(Actions.F5).interval(2000).schedule(310000))
//                .add(new CastSpell(Actions._5).interval(2000))
//                .add(new UsePotion(1).after(Actions._5, 5).interval(1500))
//                .add(new UsePotion(3).after(Actions._5, 6).interval(1500));

        Screen screen = new Screen();
        s.add(
                new CastSpell(Actions.F4).interval(2000).schedule(361_000))
                .add(new CastSpell(Actions.F5).interval(2000).schedule(310_000))
                .add(new CastSpell(Actions._5).interval(3000))
                .add(new UsePotion(1).after(Actions._5, 20).interval(1200)) //res pot
                .add(new UsePotion(3).after(Actions._5, 30).interval(1200)) //mana pot
                .add(new UsePotion(2).interval(1200).schedule(360_000)) //hp pot
                .add(new Walk(screen.getYCenteredXMaximumLeftCoordinates(), 2000).after(Actions._5, 10).interval(500))
                .add(new Walk(screen.getYCenteredXMaximumRightCoordinates(), 2000).after(Actions._5, 12));

        b.run(s).loop(12);
    }
}
