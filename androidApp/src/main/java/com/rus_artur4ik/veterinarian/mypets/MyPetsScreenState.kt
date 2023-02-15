package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.veterinarian.common.mvvm.CoreState

data class NewWorkoutState(
    val petNameFilter: String,
    val items: List<PetItem>
): CoreState()

data class PetItem(
    val petName: String,
    val petKind: String,
    val petBreed: String
)