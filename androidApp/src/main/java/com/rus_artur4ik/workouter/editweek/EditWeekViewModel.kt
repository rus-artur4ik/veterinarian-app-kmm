package com.rus_artur4ik.workouter.editweek

import com.rus_artur4ik.workouter.Day.FRIDAY
import com.rus_artur4ik.workouter.Day.MONDAY
import com.rus_artur4ik.workouter.Day.SATURDAY
import com.rus_artur4ik.workouter.Day.SUNDAY
import com.rus_artur4ik.workouter.Day.THURSDAY
import com.rus_artur4ik.workouter.Day.TUESDAY
import com.rus_artur4ik.workouter.Day.WEDNESDAY
import com.rus_artur4ik.workouter.DayEntity
import com.rus_artur4ik.workouter.ExerciseEntity
import com.rus_artur4ik.workouter.common.CoreViewModel

class EditWeekViewModel: CoreViewModel<EditWeekState>() {

    override fun provideInitialScreenState(): EditWeekState {
        val twentyNinethOfAugust = 19233L
        return EditWeekState(
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