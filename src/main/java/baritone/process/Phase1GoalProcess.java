/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.process;

import baritone.Baritone;
import baritone.ai.phase1.GoalExecutionContext;
import baritone.ai.phase1.Phase1GoalPlans;
import baritone.ai.phase1.SequentialGoalPlan;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;

/**
 * Lightweight process that drives the phase 1 multi-step goal plan while deferring pathing control
 * to existing Baritone processes (mine/custom goal).
 */
public final class Phase1GoalProcess extends BaritoneProcessHelper {

    private final GoalExecutionContext executionContext;
    private SequentialGoalPlan plan;

    public Phase1GoalProcess(Baritone baritone) {
        super(baritone);
        this.executionContext = new GoalExecutionContext(baritone);
    }

    public void startStarterPlan() {
        this.plan = new SequentialGoalPlan(executionContext, Phase1GoalPlans.starterPlan());
    }

    public void stop() {
        if (plan != null) {
            baritone.getMineProcess().cancel();
            baritone.getCustomGoalProcess().onLostControl();
        }
        this.plan = null;
    }

    public String currentGoalName() {
        return plan == null ? "<none>" : plan.currentGoalName();
    }

    @Override
    public boolean isActive() {
        return plan != null;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        if (plan == null) {
            return new PathingCommand(null, PathingCommandType.DEFER);
        }
        plan.tick();
        if (plan.isCompleted()) {
            stop();
        }
        return new PathingCommand(null, PathingCommandType.DEFER);
    }

    @Override
    public void onLostControl() {
        stop();
    }

    @Override
    public String displayName0() {
        return "Phase 1 Goal Plan (" + currentGoalName() + ")";
    }
}
