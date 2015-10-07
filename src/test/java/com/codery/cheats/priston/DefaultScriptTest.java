package com.codery.cheats.priston;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import static com.codery.cheats.priston.BaseAction.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by thomasadriano on 26/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultScriptTest {

    @Mock
    private GameAction act1;
    @Mock
    private GameAction act2;
    @Mock
    private GameAction act3;
    @Mock
    private SmarterRobot robot;

    private static final Set TEST_CONSTRAINTS = new HashSet<>(Arrays.asList(new ConstraintAfter(Actions._6, 2)));

    private DefaultScript script;

    @Before
    public void setUpMethod() {
        configureAction(act1);
        configureAction(act2);
        configureAction(act3);

        script = new DefaultScript(robot);
    }

    @Test
    public void shouldBePossible_ToAddActions() {
        List<GameAction> expected = Arrays.asList(act1, act2, act3);

        for (GameAction act : expected) {
            script.add(act);
        }

        assertTrue(expected.containsAll(script.getActions()));
    }

    /**
     * Take the word Empty in the method's name as actions with all data (baseInterval, schedule...) set to default
     */
    @Test
    public void shouldExecuteActions_When_ActionsAreEmpty() {
        script.add(act1).add(act2).add(act3);
        script.execute();

        verify(act1).execute(robot);
        verify(act2).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldExecuteActions_When_ActionsHaveConstraints() {
        configureActions(Arrays.asList(act1, act2), (act) -> when(act.getConstraints()).thenReturn(TEST_CONSTRAINTS));

        script.add(act1).add(act2).add(act3);
        script.execute();

        //only act 3 should've been executed
        verify(act1, never()).execute(robot);
        verify(act2, never()).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldExecuteActions_When_AllConfigurationsAreSet_And_AllMatch() {
        configureActions(Arrays.asList(act1, act2, act3), (act) -> {
            when(act.getSchedule()).thenReturn(60000l);
            when(act.getInterval()).thenReturn(200l);
            when(act.getTimes()).thenReturn(1);
            when(act.getLastExecution()).thenReturn((long) System.currentTimeMillis() - 61000l);
            when(act.getConstraints()).thenReturn(TEST_CONSTRAINTS);
        });

        script.ACTIONS_COUNTER.put(Actions._6, 2);
        script.add(act1).add(act2).add(act3);
        script.execute();

        verify(act1).execute(robot);
        verify(act2).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldNotExecuteActions_When_AllConfigurationsAreSet_And_OneDontMatch() {
        configureActions(Arrays.asList(act1, act2, act3), (act) -> {
            when(act.getSchedule()).thenReturn(60000l);
            when(act.getInterval()).thenReturn(200l);
            when(act.getTimes()).thenReturn(1);
            when(act.getLastExecution()).thenReturn((long) System.currentTimeMillis() - 100);
            when(act.getConstraints()).thenReturn(TEST_CONSTRAINTS);
        });

        script.ACTIONS_COUNTER.put(Actions._6, 2);
        script.add(act1).add(act2).add(act3);
        script.execute();

        verify(act1, never()).execute(robot);
        verify(act2, never()).execute(robot);
        verify(act3, never()).execute(robot);
    }


    @Test
    public void shouldNotExecuteActions_When_ActionsHaveSchedule_And_ScheduleDontMatch() {
        configureActions(Arrays.asList(act1, act2), (act) -> {
            when(act.getSchedule()).thenReturn(60000l);
        });

        script.add(act1).add(act2).add(act3);

        script.execute();

        verify(act1, never()).execute(robot);
        verify(act2, never()).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldExecuteActions_When_ActionsHaveSchedule_And_ScheduleMatch() {
        configureActions(Arrays.asList(act1, act2), (act) -> {
            when(act.getSchedule()).thenReturn(60000l);
            when(act.getLastExecution()).thenReturn(System.currentTimeMillis() - 65000l);
        });

        script.add(act1).add(act2).add(act3);

        script.execute();

        verify(act1).execute(robot);
        verify(act2).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldAlwaysExecuteActions_When_ActionWasNotExecutedYet_And_ThereAreNoConstraints() {
        configureActions(Arrays.asList(act1, act2, act3), (act) -> {
            when(act.getSchedule()).thenReturn(60000l);
            when(act.getInterval()).thenReturn(200l);
            when(act.getTimes()).thenReturn(1);
            when(act.getLastExecution()).thenReturn((long) BaseAction.NO_VALUE);
        });

        script.add(act1).add(act2).add(act3);
        script.execute();

        verify(act1).execute(robot);
        verify(act2).execute(robot);
        verify(act3).execute(robot);
    }

    @Test
    public void shouldExecuteActionsInIntervalls_When_IntervalIsSetted() {
        configureActions(Arrays.asList(act1, act3), (act) -> {
            when(act.getInterval()).thenReturn(200l);
        });

        script.add(act1).add(act2).add(act3);
        long init = System.currentTimeMillis();
        script.execute();
        long end = System.currentTimeMillis() - init;

        //400 = 200 baseInterval time + 200 processment time

        assertThat(end, is(greaterThanOrEqualTo(400l)));
        assertThat(end, is(lessThanOrEqualTo(500l)));
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void configureAction(GameAction act, Consumer<GameAction>... additionalCfg) {
        configureActions(Arrays.asList(act), additionalCfg);
    }

    private void configureActions(List<GameAction> acts, Consumer<GameAction>... additionalCfg) {
        for (GameAction act : acts) {
            when(act.getConstraints()).thenReturn(Collections.emptySet());
            when(act.getLastExecution()).thenReturn((long) System.currentTimeMillis());
            when(act.getSchedule()).thenReturn((long) NO_VALUE);
            when(act.getInterval()).thenReturn(1l);
            when(act.getTimes()).thenReturn(1);

            if (additionalCfg != null && additionalCfg.length > 0) {
                for (Consumer<GameAction> c : additionalCfg) {
                    c.accept(act);
                }
            }
        }
    }

}
