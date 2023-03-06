package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

data class MyPetsScreenState(
    val petNameFilter: String,
    val items: List<PetEntity>
): MvvmState()