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

public final class CraftCraftingTableGoal extends BotGoal {

    public CraftCraftingTableGoal() {
        super("Craft crafting table", List.of());
    }

    @Override
    public boolean isCompleted(GoalExecutionContext context) {
        return context.countItem(Items.CRAFTING_TABLE) > 0;
    }

    @Override
    public void onStart(GoalExecutionContext context) {
        // Placeholder: phase 1 framework tracks completion and ordering,
        // while crafting itself can be handled by inventory automation in a later phase.
    }
}
