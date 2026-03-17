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

import java.util.List;
import net.minecraft.world.item.Items;

public final class GatherWoodGoal extends BotGoal {

    private final int logCount;

    public GatherWoodGoal(int logCount) {
        super("Gather wood", List.of());
        this.logCount = logCount;
    }

    @Override
    public boolean isCompleted(GoalExecutionContext context) {
        return context.countItem(Items.OAK_LOG)
                + context.countItem(Items.BIRCH_LOG)
                + context.countItem(Items.SPRUCE_LOG)
                + context.countItem(Items.JUNGLE_LOG)
                + context.countItem(Items.ACACIA_LOG)
                + context.countItem(Items.DARK_OAK_LOG)
                + context.countItem(Items.MANGROVE_LOG)
                + context.countItem(Items.CHERRY_LOG) >= logCount;
    }

    @Override
    public void onStart(GoalExecutionContext context) {
        context.mineByName(logCount, "oak_log", "birch_log", "spruce_log", "jungle_log", "acacia_log", "dark_oak_log", "mangrove_log", "cherry_log");
    }
}
