package com.codery.cheats.priston;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class SmarterRobot extends Robot {

    private static final int MOUSE_LEFT_BUTTON = InputEvent.BUTTON1_DOWN_MASK;
    private static final int MOUSE_MIDDLE_BUTTON = InputEvent.BUTTON2_DOWN_MASK;
    private static final int MOUSE_RIGHT_BUTTON = InputEvent.BUTTON3_DOWN_MASK;

    public SmarterRobot() throws AWTException {
    }

    public SmarterRobot(GraphicsDevice screen) throws AWTException {
        super(screen);
    }

    void pressKey(String key) {
        keyPress(ASCIIUtils.getAsciiCode(key));
        keyRelease(ASCIIUtils.getAsciiCode(key));
    }

    void clickLeft() {
        mousePress(MOUSE_LEFT_BUTTON);
        mouseRelease(MOUSE_LEFT_BUTTON);
    }

    void clickRight() {
        mousePress(MOUSE_RIGHT_BUTTON);
        mouseRelease(MOUSE_RIGHT_BUTTON);
    }

    void pressLeft(Screen.ScreenCoordinate coor, long time) {
        pressLeft(coor.getX(), coor.getY(), time);
    }

    void pressLeft(int x, int y, long time) {
        mouseMove(x, y);
        mousePress(MOUSE_LEFT_BUTTON);
        sleep(time);
        mouseRelease(MOUSE_LEFT_BUTTON);
    }


    static void sleep(long millis) {
        if (millis < 0) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
