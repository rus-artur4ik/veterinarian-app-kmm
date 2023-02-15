package com.rus_artur4ik.veterinarian.home

import com.rus_artur4ik.veterinarian.common.mvvm.CoreState
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

data class HomeScreenState(
    val pets: List<PetEntity>,
    val lastVisit: VisitEntity
) : CoreState()
