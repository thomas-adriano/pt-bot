package com.codery.cheats.priston;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by thomasadriano on 27/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    public SmarterRobot robot;

    @Test
    public void usePotionShouldIncrement_ActionsCounter_WhenExecuted() {
        DefaultScript.ACTIONS_COUNTER.put(Actions._1, 0);
        UsePotion pot = new UsePotion(1);
        for (int i = 1; i <= 50; i++) {
            pot.execute(robot);
            assertThat(DefaultScript.ACTIONS_COUNTER.get(Actions._1), is(i));
        }
    }

    @Test
    public void castSpellShouldIncrement_ActionsCounter_WhenExecuted() {
        CastSpell spell = new CastSpell(Actions.F4);
        for (int i = 1; i <= 50; i++) {
            spell.execute(robot);
            assertThat(DefaultScript.ACTIONS_COUNTER.get(Actions.F4), is(i));
        }
    }

    @Test
    public void usePotionShouldAccept_OnlyPotionKeys() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Potion keys should be one of these: 1, 2, 3. But was: " + 6);

        UsePotion pot = new UsePotion(6);
        pot.doExecute(robot);
    }

    @Test
    public void castSpellShouldAccept_OnlySpellKeys() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Spell keys should be one of these: " +
                "4, 5, 6, 7, 8, 9, 0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11 ,F12. " +
                "But was: " + 2);

        CastSpell spell = new CastSpell(Actions._2);
        spell.doExecute(robot);
    }

    @Test
    public void intervalVariationShouldRespect_IntervalVariationPercentageSetted() {
        BaseAction act = new BaseAction(Actions._1, 50) {
            @Override
            protected void doExecute(SmarterRobot r) {
                System.out.println("NOOP");
            }
        };

        act.interval(1000);

        for (int i = 0; i < 1000; i++) {
            act.execute(robot);

            assertThat(act.getInterval(), is(lessThanOrEqualTo(1500l)));
            assertThat(act.getInterval(), is(greaterThanOrEqualTo(1000l)));
        }
    }

    @Test
    public void actionShouldRespectIntervalTime() {
        BaseAction act = new BaseAction(Actions._1, 0) {
            @Override
            protected void doExecute(SmarterRobot r) {
                System.out.println("NOOP");
            }
        };

        act.interval(5000);

        act.execute(robot);

        verify(robot);
		SmarterRobot.sleep(5000);
    }

    @Test
    public void actionShouldRefreshLastExecution_AfterItIsExecuted() {
        BaseAction act = new BaseAction(Actions._1, 0) {
            @Override
            protected void doExecute(SmarterRobot r) {
                System.out.println("NOOP");
            }
        };

        assertThat(act.getLastExecution(), is((long) BaseAction.NO_VALUE));

        long expectedLastExecution = System.currentTimeMillis();
        act.execute(robot);

        assertThat(act.getLastExecution(), is(greaterThanOrEqualTo(expectedLastExecution)));
        assertThat(act.getLastExecution(), is(lessThanOrEqualTo(expectedLastExecution + 500)));
    }

}
