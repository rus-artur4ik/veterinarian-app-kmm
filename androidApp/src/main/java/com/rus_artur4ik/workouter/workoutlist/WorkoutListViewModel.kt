package com.rus_artur4ik.workouter.workoutlist

import com.rus_artur4ik.workouter.common.CoreViewModel

class WorkoutListViewModel : CoreViewModel<WorkoutListState>() {

    override fun provideInitialScreenState(): WorkoutListState {
        return WorkoutListState()
    }
}