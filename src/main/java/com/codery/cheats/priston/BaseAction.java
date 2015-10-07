package com.codery.cheats.priston;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by thomasadriano on 24/09/15.
 */
public abstract class BaseAction implements GameAction {

    static final int NO_VALUE = -1;
    protected final Actions key;
    protected int times = 1;
    protected long baseInterval = 2000;
    protected long effectiveInterval;
    protected long schedule = NO_VALUE;
    protected long lastExecution = NO_VALUE;
    protected double variationPercentage = 10;
    protected Set<ConstraintAfter> afterConstraints = new HashSet<>();

    public BaseAction(Actions spell) {
        this(spell, 10);
    }

    public BaseAction(Actions spell, double intervalVariationPercentage) {
        this.key = spell;
        this.variationPercentage = intervalVariationPercentage;
    }

    @Override
    public BaseAction times(int times) {
        this.times = times;
        return this;
    }

    @Override
    public BaseAction interval(long millis) {
        this.baseInterval = millis;
        return this;
    }

    @Override
    public BaseAction schedule(long millis) {
        this.schedule = millis;
        return this;
    }

    @Override
    public int getTimes() {
        return times;
    }

    @Override
    public long getInterval() {
        return effectiveInterval;
    }

    @Override
    public void execute(SmarterRobot r) {
        System.out.println("Executing "+toString());
        calcIntervalVariation();
        doExecute(r);
        System.out.println("    - effective action interval: "+effectiveInterval);
        waitInterval(r);
        refreshLastExecution();
        incTimesUsed();
    }

    private void waitInterval(SmarterRobot r) {
        r.sleep(this.getInterval());
    }

    private void incTimesUsed() {
        Integer timesUsed = (Integer) DefaultScript.ACTIONS_COUNTER.get(key);
        if (timesUsed == null) {
            timesUsed = 0;
        }

        timesUsed++;

        DefaultScript.ACTIONS_COUNTER.put(key, timesUsed);
    }

    private void refreshLastExecution() {
        lastExecution = System.currentTimeMillis();
    }

    protected abstract void doExecute(SmarterRobot r);

    private void calcIntervalVariation() {
        double init = baseInterval;
        double ratio = (variationPercentage / 100d) + 1d;
        double end = (baseInterval * ratio);
        effectiveInterval = (long) (Math.random() * ((end - init) + 1) + init);
    }

    @Override
    public long getSchedule() {
        return schedule;
    }

    @Override
    public long getLastExecution() {
        return lastExecution;
    }

    @Override
    public Set<ConstraintAfter> getConstraints() {
        return afterConstraints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Action: ").append(key).append(", ").append("Interval: ").append(baseInterval).append(" | " + variationPercentage + "% variation");

        if (times != 1) {
            sb.append(", ").append("Times: ").append(times);
        }

        if (schedule != -1) {
            sb.append(", ").append("Schedule: ").append(schedule).append(" to ").append(schedule).append(" millis");
        }

        if (!afterConstraints.isEmpty()) {
            sb.append(", ").append("Constraints: ").append(afterConstraints);
        }

        return sb.toString();
    }

    @Override
    public GameAction after(Actions act, int times) {
        afterConstraints.add(new ConstraintAfter(act, times));
        return this;
    }

    static final class ConstraintAfter implements Constraint {
        private final Actions act;
        private final int times;
        private int lastActualTimesExecuted;

        ConstraintAfter(Actions act, int times) {
            this.act = act;
            this.times = times;
            this.lastActualTimesExecuted = 0;
        }

        public Actions getAction() {
            return act;
        }

        public int getTimes() {
            return times;
        }

        public boolean fulfills(int actualTimesExecuted) {
            if ((lastActualTimesExecuted == 0 && actualTimesExecuted >= times)
                    || (lastActualTimesExecuted != 0 && (actualTimesExecuted - lastActualTimesExecuted) >= times)) {
                lastActualTimesExecuted = actualTimesExecuted;
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ConstraintAfter that = (ConstraintAfter) o;

            if (times != that.times) return false;
            return act.equals(that.act);

        }

        @Override
        public int hashCode() {
            int result = act.hashCode();
            result = 31 * result + times;
            return result;
        }

        @Override
        public String toString() {
            return "After " + act + " was executed " + times + " times.";
        }
    }

}
