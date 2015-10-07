package com.codery.cheats.priston;

import java.awt.*;

/**
 * Created by thomasadriano on 29/09/15.
 */
public class Screen {

    private final Dimension scrennSize = Toolkit.getDefaultToolkit().getScreenSize();
    //base 1 result, to use it we need to convert to base 0
    private final int maxX = ((int) scrennSize.getWidth()) - 1;
    private final int maxY = ((int) scrennSize.getHeight()) - 1;
    private final int minX = 0;
    private final int minY = 0;
    private final int centerX = maxX / 2;
    private final int centerY = maxY / 2;

    public ScreenCoordinate getCentralCoordinates() {
        return new ScreenCoordinate(maxX / 2, maxY / 2);
    }

    public ScreenCoordinate getYCenteredXMaximumLeftCoordinates() {
        return new ScreenCoordinate(0, centerY);
    }

    public ScreenCoordinate getYCenteredXMaximumRightCoordinates() {
        return new ScreenCoordinate(maxX, centerY);
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public static class ScreenCoordinate {
        private final int x;
        private final int y;

        public ScreenCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
