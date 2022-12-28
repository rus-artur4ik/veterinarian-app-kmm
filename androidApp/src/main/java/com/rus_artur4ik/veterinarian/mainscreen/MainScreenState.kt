package com.rus_artur4ik.veterinarian.mainscreen

import com.rus_artur4ik.veterinarian.DayEntity
import com.rus_artur4ik.veterinarian.common.mvvm.CoreState

data class MainScreenState(
    val selectedDay: Int,
    val days: List<DayEntity>
) : CoreState()
