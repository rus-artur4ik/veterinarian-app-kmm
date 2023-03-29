package com.rus_artur4ik.veterinarian.mypets

import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceeViewModel
import com.rus_artur4ik.veterinarian.common.VetScreen.PetInfoScreen
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.coroutines.launch

class MyPetsViewModel : LceeViewModel<MyPetsScreenState>() {

    private val repository = VetRepository()
    init {
        viewModelScope.launch {
            val pets = repository.getPets()

            emitState(
                LceState.Content(
                    MyPetsScreenState(
                        petNameFilter = "",
                        items = pets
                    )
                )
            )
        }
    }

    override fun isContentEmpty(content: MyPetsScreenState) = content.items.isEmpty()

    override fun provideInitialScreenState(): LceState<MyPetsScreenState> {
        return LceState.Loading()
    }

    fun openPetInfo(pet: PetEntity) {
        navigate(PetInfoScreen) //todo
    }

    fun petNameFilterChanged(newName: String) {
        //TODO updateList
    }

    private fun updateList(newName: String) {
        state.doIfContent {
            emitState(
                LceState.Content(
                    it.copy(petNameFilter = newName)
                )
            )
        }

        viewModelScope.launch {
            val pets = repository.getPets()

            emitState(
                LceState.Content(
                    MyPetsScreenState(
                        petNameFilter = newName,
                        items = pets
                    )
                )
            )
        }
    }
}

