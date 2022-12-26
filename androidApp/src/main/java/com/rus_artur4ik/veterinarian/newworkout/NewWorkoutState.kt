package com.rus_artur4ik.veterinarian.newworkout

import com.rus_artur4ik.veterinarian.common.CoreState

data class NewWorkoutState(
    val workoutName: String,
    val items: List<WorkoutItem>
): CoreState()

sealed class WorkoutItem {
    data class WeekItem(val ordinal: Int, val showDelete: Boolean = false): WorkoutItem()
    data class DayItem(val dayName: String, val maxWeight: Int): WorkoutItem()
}