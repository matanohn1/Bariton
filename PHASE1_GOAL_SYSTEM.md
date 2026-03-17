# Phase 1 Goal System (Starter Implementation)

This repository now includes a starter **multi-step goal framework** in `baritone.ai.phase1`.

## Added classes

- `BotGoal` - base class with `name`, `subGoals`, and lifecycle methods.
- `GoalExecutionContext` - wraps Baritone APIs (including `setGoalAndPath` and mining helpers).
- `SequentialGoalPlan` - executes goals in sequence, flattening nested sub-goals.
- `GatherWoodGoal` - mines logs until a target quantity is reached.
- `CraftCraftingTableGoal` - completion check for crafting table possession.
- `MineStoneGoal` - mines stone/cobblestone until a target quantity is reached.
- `Phase1GoalPlans` - example `starterPlan()` (`wood -> crafting table -> stone`).

## How this is now wired in Baritone

This is integrated through a real process + command:

- `Phase1GoalProcess` drives `SequentialGoalPlan` each tick and is registered in `Baritone`.
- `#phase1 start` starts the starter chain.
- `#phase1 status` shows the current active phase goal.
- `#phase1 stop` cancels phase execution.

## Notes

- This is intentionally small and safe for Phase 1.
- The crafting step currently only checks completion (`has crafting table`) and leaves inventory UI automation to later phases.
- The framework is designed so Phase 2 can add condition-driven branching without replacing the current model.
