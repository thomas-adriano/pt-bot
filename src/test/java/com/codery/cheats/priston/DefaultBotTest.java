package com.codery.cheats.priston;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by thomasadriano on 24/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultBotTest {

    @Mock
    private ExecutorService executorService;
    @Mock
    private Script script;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private DefaultBot bot;

    @Before
    public void setUpMethod() {
        bot = new DefaultBot(executorService);
    }

    @Test
    public void shouldExecuteScripts_Loop() {
        bot.run(script).loop(10);
        verify(script, times(10)).execute();
    }

    @Test
    public void shouldExecuteScripts_Once() {
        bot.run(script).once();
        verify(script, times(1)).execute();
    }

    @Test
    public void shouldExecuteScripts_Forever() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Reached 10.000 times.");

        List<Integer> actualTimes = new ArrayList<>();
        final int tresholdTimes = 10000;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                if (actualTimes.size() == tresholdTimes) {
                    throw new RuntimeException("Reached 10.000 times.");
                } else {
                    actualTimes.add(1);
                }
                return null;
            }
        }).when(script).execute();

        bot.run(script).forever();
    }

    @Test
    public void shouldCreate_ADefaultScript_WhenCreateScriptIsCalled() {
        Script s = bot.createScript();
        assertTrue("Type of script created should be " + DefaultScript.class.getSimpleName(), s.getClass().equals(DefaultScript.class));
    }

    @Test
    public void shouldBePossible_ToSetStopCommand() {
        assertThat("If no exit cmd is setted, should use the default exit cmd", bot.getStopCommand(), is(Bot.DEFAULT_STOP_CMD));

        bot.setStopCommand("a", "b", "c");
        assertThat(bot.getStopCommand(), is(new String[]{"a", "b", "c"}));
    }

}
