package com.codery.cheats.priston;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by thomasadriano on 24/09/15.
 */
public class DefaultScript implements Script {

    private final List<GameAction> actions = new ArrayList<>();
    static final Map<Actions, Integer> ACTIONS_COUNTER = new ConcurrentHashMap<>();
    private final SmarterRobot robot;
    private final String name;
    	
    DefaultScript(SmarterRobot robot, String scriptName) {
        this.robot = robot;
        this.name = scriptName;
    }

    @Override
    public Script add(GameAction act) {
        actions.add(act);
        return this;
    }

    @Override
    public void execute() {
        actions.forEach((act) -> {

                    long currentTime = System.currentTimeMillis();
                    long delta = currentTime - act.getLastExecution();
                    boolean wasExecuted = act.getLastExecution() != BaseAction.NO_VALUE;
                    boolean hasSchedule = act.getSchedule() != BaseAction.NO_VALUE;
                    boolean scheduleOver = ((wasExecuted && hasSchedule) && delta >= act.getSchedule());

                    boolean allConstraintsFulfilled = true;
                    for (GameAction.Constraint constraint : act.getConstraints()) {
                        Integer timesExecuted = ACTIONS_COUNTER.get(constraint.getAction());
                        timesExecuted = timesExecuted == null ? 0 : timesExecuted;

                        if (!constraint.fulfills(timesExecuted)) {
                            allConstraintsFulfilled = false;
                            break;
                        }
                    }

                    if ((scheduleOver || !wasExecuted || !hasSchedule) && allConstraintsFulfilled) {
                        for (int i = 0; i < act.getTimes(); i++) {
                            act.execute(robot);
                        }
                    }

                }
        );
    }

    List<GameAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

    @Override
    public void printSummary() {
        System.out.println("                        SCRIPT \""+name+"\" SUMMARY                        ");
        actions.forEach((act) ->
                {
                    System.out.println(act.toString());
                }
        );
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }

	@Override
	public String getName() {
		return name;
	}
}
