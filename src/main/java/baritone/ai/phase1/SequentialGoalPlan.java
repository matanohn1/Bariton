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

package baritone.ai.phase1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Simple phase 1 goal runner: executes goals in order and supports nested sub-goals.
 */
public final class SequentialGoalPlan {

    private final GoalExecutionContext context;
    private final List<BotGoal> flatGoals;
    private int currentIndex;
    private BotGoal activeGoal;

    public SequentialGoalPlan(GoalExecutionContext context, List<BotGoal> rootGoals) {
        this.context = Objects.requireNonNull(context);
        this.flatGoals = flatten(Objects.requireNonNull(rootGoals));
    }

    public boolean isCompleted() {
        return currentIndex >= flatGoals.size();
    }

    public String currentGoalName() {
        return activeGoal == null ? "<none>" : activeGoal.name();
    }

    public void tick() {
        if (isCompleted()) {
            return;
        }
        if (activeGoal == null) {
            activeGoal = flatGoals.get(currentIndex);
            activeGoal.onStart(context);
        }
        if (activeGoal.isCompleted(context)) {
            activeGoal.onStop(context);
            currentIndex++;
            activeGoal = null;
            return;
        }
        activeGoal.onTick(context);
    }

    private static List<BotGoal> flatten(List<BotGoal> goals) {
        List<BotGoal> result = new ArrayList<>();
        for (BotGoal goal : goals) {
            if (!goal.subGoals().isEmpty()) {
                result.addAll(flatten(goal.subGoals()));
            }
            result.add(goal);
        }
        return result;
    }
}
