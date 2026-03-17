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
import java.util.Collections;
import java.util.List;

/**
 * Phase 1 structured goal primitive.
 */
public abstract class BotGoal {

    private final String name;
    private final List<BotGoal> subGoals;

    protected BotGoal(String name, List<BotGoal> subGoals) {
        this.name = name;
        this.subGoals = Collections.unmodifiableList(new ArrayList<>(subGoals));
    }

    public final String name() {
        return name;
    }

    public final List<BotGoal> subGoals() {
        return subGoals;
    }

    public abstract boolean isCompleted(GoalExecutionContext context);

    public void onStart(GoalExecutionContext context) {
    }

    public void onTick(GoalExecutionContext context) {
    }

    public void onStop(GoalExecutionContext context) {
    }
}
