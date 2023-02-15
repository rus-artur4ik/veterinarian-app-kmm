package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.veterinarian.common.Screen
import com.rus_artur4ik.veterinarian.common.mvvm.CoreViewModel
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex

class MyPetsViewModel: CoreViewModel<MyPetsScreenState>() {

    override fun provideInitialScreenState(): MyPetsScreenState {
        return MyPetsScreenState(
            petNameFilter = "",
            items = listOf(
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
            )
        )
    }

    fun openPetInfo(pet: PetEntity) {
        navigate(Screen.PetInfoScreen)
    }

    fun petNameFilterChanged(newName: String) {
        emitState(
            state.value.copy(petNameFilter = newName)
        )
    }
}

