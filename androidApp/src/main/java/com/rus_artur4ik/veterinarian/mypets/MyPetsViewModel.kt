package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceeViewModel
import com.rus_artur4ik.veterinarian.VetScreen.PetInfoScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreenState.SearchMode
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreenState.ShowAllMode

class MyPetsViewModel : LceeViewModel<MyPetsScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    init {
        emitStateAsync {
            ShowAllMode(repository.getPets())
        }
    }

    override fun isContentEmpty(content: MyPetsScreenState) = when (content) {
        is SearchMode -> content.items.isEmpty()
        is ShowAllMode -> content.items.isEmpty()
    }

    override fun provideInitialScreenState(): LceState<MyPetsScreenState> {
        return LceState.Loading()
    }

    fun openPetInfo(pet: PetEntity) {
        navigate(PetInfoScreen) //todo
    }

    fun goToSearchMode() {
        emitState(
            LceState.Content(
                SearchMode(items = emptyList(), petNameFilter = "")
            )
        )
    }

    fun goToShowAllMode() {
        emitState(LceState.Loading())
        emitStateAsync {
            ShowAllMode(repository.getPets())
        }
    }

    fun petNameFilterChanged(newName: String) {
        //TODO updateList
    }

    private fun updateList(newName: String) {
//        state.doIfContent {
//            emitState(
//                SimpleLceState.Content(
//                    it.copy(petNameFilter = newName)
//                )
//            )
//        }
//
//        viewModelScope.launch {
//            val pets = repository.getPets()
//
//            emitState(
//                SimpleLceState.Content(
//                    MyPetsScreenState(
//                        petNameFilter = newName,
//                        items = pets
//                    )
//                )
//            )
//        }
    }
}

