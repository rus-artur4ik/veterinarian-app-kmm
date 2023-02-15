package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.veterinarian.common.mvvm.CoreViewModel

class MyPetsViewModel: CoreViewModel<NewWorkoutState>() {

    override fun provideInitialScreenState(): NewWorkoutState {
        return NewWorkoutState(
            petNameFilter = "",
            items = listOf(
                PetItem(
                    petName = "Кеша",
                    petKind = "Кот",
                    petBreed = "Ориентальная кошка"
                ),
                PetItem(
                    petName = "Тузик",
                    petKind = "Собака",
                    petBreed = "Немецкая овчарка"
                ),
                PetItem(
                    petName = "Кеша",
                    petKind = "Кот",
                    petBreed = "Ориентальная кошка"
                ),
                PetItem(
                    petName = "Тузик",
                    petKind = "Собака",
                    petBreed = "Немецкая овчарка"
                ),
                PetItem(
                    petName = "Кеша",
                    petKind = "Кот",
                    petBreed = "Ориентальная кошка"
                ),
                PetItem(
                    petName = "Тузик",
                    petKind = "Собака",
                    petBreed = "Немецкая овчарка"
                ),
                PetItem(
                    petName = "Кеша",
                    petKind = "Кот",
                    petBreed = "Ориентальная кошка"
                ),
                PetItem(
                    petName = "Тузик",
                    petKind = "Собака",
                    petBreed = "Немецкая овчарка"
                ),
            )
        )
    }

    fun onWorkoutNameChanged(newName: String) {
        emitState(
            state.value.copy(petNameFilter = newName)
        )
    }
}

