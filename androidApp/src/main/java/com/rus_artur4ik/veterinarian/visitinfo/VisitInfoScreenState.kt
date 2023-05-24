package com.rus_artur4ik.veterinarian.visitinfo

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

data class VisitInfoScreenState(
    val visit: VisitEntity
): MvvmState