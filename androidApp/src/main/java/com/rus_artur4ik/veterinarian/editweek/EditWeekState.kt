package com.rus_artur4ik.veterinarian.editweek

import com.rus_artur4ik.veterinarian.DayEntity
import com.rus_artur4ik.veterinarian.common.CoreState

data class EditWeekState(
    val selectedDay: Int,
    val days: List<DayEntity>
) : CoreState()
