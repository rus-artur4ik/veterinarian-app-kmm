package com.rus_artur4ik.veterinarian.homescreen

import com.rus_artur4ik.veterinarian.DayEntity
import com.rus_artur4ik.veterinarian.common.mvvm.CoreState

data class HomeScreenState(
    val selectedDay: Int,
    val days: List<DayEntity>
) : CoreState()
