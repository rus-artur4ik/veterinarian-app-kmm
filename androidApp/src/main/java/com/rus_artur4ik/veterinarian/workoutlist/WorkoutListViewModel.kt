package com.rus_artur4ik.veterinarian.workoutlist

import com.rus_artur4ik.veterinarian.common.CoreViewModel

class WorkoutListViewModel : CoreViewModel<WorkoutListState>() {

    override fun provideInitialScreenState(): WorkoutListState {
        return WorkoutListState()
    }
}