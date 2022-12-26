package com.rus_artur4ik.veterinarian.newworkout

import com.rus_artur4ik.veterinarian.common.CoreViewModel
import com.rus_artur4ik.veterinarian.newworkout.WorkoutItem.DayItem
import com.rus_artur4ik.veterinarian.newworkout.WorkoutItem.WeekItem

class NewWorkoutViewModel: CoreViewModel<NewWorkoutState>() {

    override fun provideInitialScreenState(): NewWorkoutState {
        return NewWorkoutState(
            workoutName = "",
            items = listOf(
                WeekItem(1),
                DayItem("Monday", 50),
                DayItem("Wednesday", 60),
                DayItem("Friday", 60),
                WeekItem(2, true),
                DayItem("Tuesday", 60),
                DayItem("Thursday", 70),
                DayItem("Saturday", 80),
            )
        )
    }

    fun onWorkoutNameChanged(newName: String) {
        emitState(
            state.value.copy(workoutName = newName)
        )
    }
}

