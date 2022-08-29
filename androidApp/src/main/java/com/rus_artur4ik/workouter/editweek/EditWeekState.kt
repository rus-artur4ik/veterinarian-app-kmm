package com.rus_artur4ik.workouter.editweek

import com.rus_artur4ik.workouter.DayEntity
import com.rus_artur4ik.workouter.common.CoreState

data class EditWeekState(
    val selectedDay: Int,
    val days: List<DayEntity>
) : CoreState()
