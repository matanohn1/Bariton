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

package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.process.Phase1GoalProcess;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class Phase1Command extends Command {

    private final Phase1GoalProcess phase1GoalProcess;

    public Phase1Command(IBaritone baritone, Phase1GoalProcess phase1GoalProcess) {
        super(baritone, "phase1");
        this.phase1GoalProcess = phase1GoalProcess;
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action = args.getAsOrDefault(String.class, "status");
        args.requireMax(1);
        if ("start".equalsIgnoreCase(action)) {
            phase1GoalProcess.startStarterPlan();
            logDirect("Started phase 1 starter plan");
            return;
        }
        if ("stop".equalsIgnoreCase(action)) {
            phase1GoalProcess.stop();
            logDirect("Stopped phase 1 starter plan");
            return;
        }
        if ("status".equalsIgnoreCase(action)) {
            if (!phase1GoalProcess.isActive()) {
                throw new CommandInvalidStateException("Phase 1 plan is not active");
            }
            logDirect("Phase 1 current goal: " + phase1GoalProcess.currentGoalName());
            return;
        }
        throw new CommandException("Expected one of: start, stop, status");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.of("start", "stop", "status");
    }

    @Override
    public String getShortDesc() {
        return "Manage the phase 1 multi-step goal plan";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList(
                "Runs the phase 1 starter chain: gather wood -> craft table -> mine stone.",
                "",
                "Usage:",
                "> phase1 start - Start the phase 1 starter plan",
                "> phase1 stop - Stop the phase 1 starter plan",
                "> phase1 status - Show the currently active phase 1 goal"
        );
    }
}
