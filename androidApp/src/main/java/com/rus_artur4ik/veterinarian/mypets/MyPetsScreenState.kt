package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

sealed class MyPetsScreenState: MvvmState {

    data class ShowAllMode(val items: List<PetEntity>): MyPetsScreenState()

    data class SearchMode(val petNameFilter: String, val items: List<PetEntity>): MyPetsScreenState()
}