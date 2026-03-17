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

## How to wire this in Baritone

A minimal integration path is:

1. Instantiate a `SequentialGoalPlan` from your command/process entrypoint.
2. Call `tick()` every game tick while your process is active.
3. Stop when `isCompleted()` returns true.

Example (pseudo-code):

```java
GoalExecutionContext context = new GoalExecutionContext(baritone);
SequentialGoalPlan plan = new SequentialGoalPlan(context, Phase1GoalPlans.starterPlan());

// each tick
plan.tick();
if (plan.isCompleted()) {
    // done
}
```

## Notes

- This is intentionally small and safe for Phase 1.
- The crafting step currently only checks completion (`has crafting table`) and leaves inventory UI automation to later phases.
- The framework is designed so Phase 2 can add condition-driven branching without replacing the current model.
