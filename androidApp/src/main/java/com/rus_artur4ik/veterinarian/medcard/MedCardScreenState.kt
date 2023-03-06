package com.rus_artur4ik.veterinarian.medcard

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

data class MedCardScreenState(
    val petNameFilter: String,
    val visits: List<VisitEntity>
): MvvmState()