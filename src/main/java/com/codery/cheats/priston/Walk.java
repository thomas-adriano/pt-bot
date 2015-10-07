package com.codery.cheats.priston;

/**
 * Created by thomasadriano on 28/09/15.
 */
public class Walk extends BaseAction {

    private final Screen.ScreenCoordinate coord;
    private final long time;

    public Walk(Screen.ScreenCoordinate coord, long time) {
        super(Actions.WALK);
        this.coord = coord;
        this.time = time;
    }

    @Override
    protected void doExecute(SmarterRobot r) {
        r.pressLeft(coord, time);
        Screen s = new Screen();
        r.mouseMove(s.getCenterX(), s.getCenterY());
    }
}
