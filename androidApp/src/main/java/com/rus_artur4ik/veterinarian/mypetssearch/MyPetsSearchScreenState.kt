package com.rus_artur4ik.veterinarian.mypetssearch

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

data class MyPetsSearchScreenState(
    val state: State,
    val petNameFilter: String,
    val items: List<PetEntity>
): MvvmState {

    enum class State {
        LOADING, CONTENT, ERROR, EMPTY
    }
}