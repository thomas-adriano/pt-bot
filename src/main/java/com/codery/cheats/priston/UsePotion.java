package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class UsePotion extends BaseAction {

    public UsePotion(int potNumber) {
        super(Actions.fromString(String.valueOf(potNumber)));
    }

    @Override
    protected void doExecute(SmarterRobot r) {
        if (key != Actions._1 && key != Actions._2 && key != Actions._3) {
            throw new RuntimeException("Potion keys should be one of these: 1, 2, 3. But was: " + key.value());
        }
        effectiveInterval -= 800;
        r.pressKey(key.value());
        SmarterRobot.sleep(800);
    }
}
