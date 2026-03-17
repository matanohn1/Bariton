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

import baritone.api.IBaritone;
import baritone.api.pathing.goals.Goal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Minimal helper context used by phase 1 goals.
 */
public final class GoalExecutionContext {

    private final IBaritone baritone;

    public GoalExecutionContext(IBaritone baritone) {
        this.baritone = baritone;
    }

    public IBaritone baritone() {
        return baritone;
    }

    public void setGoalAndPath(Goal goal) {
        baritone.getCustomGoalProcess().setGoalAndPath(goal);
    }

    public void mineByName(int quantity, String... blockNames) {
        baritone.getMineProcess().mineByName(quantity, blockNames);
    }

    public int countItem(Item item) {
        Inventory inventory = baritone.getPlayerContext().player().getInventory();
        int count = 0;
        for (ItemStack stack : inventory.items) {
            if (stack.is(item)) {
                count += stack.getCount();
            }
        }
        return count;
    }
}
