package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class CastSpell extends BaseAction {

    public CastSpell(Actions key) {
        super(key);
    }

    @Override
    protected void doExecute(SmarterRobot r) {
        if (key == Actions._1 || key == Actions._2 || key == Actions._3) {
            throw new RuntimeException("Spell keys should be one of these: " +
                    "4, 5, 6, 7, 8, 9, 0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11 ,F12. " +
                    "But was: " + key.value());
        }
        effectiveInterval -= 100;
        r.pressKey(key.value());
        r.sleep(100);
        r.clickRight();
    }

}
