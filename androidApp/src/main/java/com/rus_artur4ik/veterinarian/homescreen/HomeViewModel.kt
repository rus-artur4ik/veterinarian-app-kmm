package com.rus_artur4ik.veterinarian.homescreen

import com.rus_artur4ik.veterinarian.Day.FRIDAY
import com.rus_artur4ik.veterinarian.Day.MONDAY
import com.rus_artur4ik.veterinarian.Day.SATURDAY
import com.rus_artur4ik.veterinarian.Day.SUNDAY
import com.rus_artur4ik.veterinarian.Day.THURSDAY
import com.rus_artur4ik.veterinarian.Day.TUESDAY
import com.rus_artur4ik.veterinarian.Day.WEDNESDAY
import com.rus_artur4ik.veterinarian.DayEntity
import com.rus_artur4ik.veterinarian.ExerciseEntity
import com.rus_artur4ik.veterinarian.common.mvvm.CoreViewModel

class HomeViewModel: CoreViewModel<HomeScreenState>() {

    override fun provideInitialScreenState(): HomeScreenState {
        val twentyNinethOfAugust = 19233L
        return HomeScreenState(
            selectedDay = 0,
            days = listOf(
                DayEntity(
                    MONDAY,
                    twentyNinethOfAugust,
                    listOf()
                ),
                DayEntity(
                    TUESDAY,
                    twentyNinethOfAugust + 1,
                    listOf()
                ),
                DayEntity(
                    WEDNESDAY,
                    twentyNinethOfAugust + 2,
                    listOf()
                ),
                DayEntity(
                    THURSDAY,
                    twentyNinethOfAugust + 3,
                    listOf()
                ),
                DayEntity(
                    FRIDAY,
                    twentyNinethOfAugust + 4,
                    listOf(
                        ExerciseEntity(
                            "Подтягивания",
                            6,
                            0,
                            120
                        )
                    )
                ),
                DayEntity(
                    SATURDAY,
                    twentyNinethOfAugust + 5,
                    listOf()
                ),
                DayEntity(
                    SUNDAY,
                    twentyNinethOfAugust + 6,
                    listOf()
                ),
            )
        )
    }

    fun selectDay(index: Int) {
        emitState(state.value.copy(selectedDay = index))
    }
}