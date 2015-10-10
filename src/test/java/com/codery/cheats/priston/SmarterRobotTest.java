package com.codery.cheats.priston;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Created by thomasadriano on 27/09/15.
 */
public class SmarterRobotTest {

    @Test
    public void shouldSleep_theIntervalGiven() throws AWTException {
        long init = System.currentTimeMillis();

        SmarterRobot.sleep(2000);

        long end = System.currentTimeMillis();
        long elapsed = end - init;
        assertThat(elapsed, is(greaterThanOrEqualTo(2000l)));
        assertThat(elapsed, is(lessThanOrEqualTo(2000l + 500l)));
    }
}
