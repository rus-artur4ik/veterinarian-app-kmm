package com.rus_artur4ik.veterinarian.mypetssearch

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.mypetssearch.MyPetsSearchScreenState.State.CONTENT
import com.rus_artur4ik.veterinarian.mypetssearch.MyPetsSearchScreenState.State.ERROR
import com.rus_artur4ik.veterinarian.mypetssearch.MyPetsSearchScreenState.State.LOADING
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen

class MyPetsSearchViewModel : MvvmViewModel<MyPetsSearchScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    override fun provideInitialScreenState(): MyPetsSearchScreenState {
        return MyPetsSearchScreenState(
            state = LOADING,
            petNameFilter = "",
            items = listOf()
        )
    }

    init {
        emitStateAsync(
            onError = {
                state.copy(state = ERROR)
            }
        ) {
            val items = repository.getPets()
            MyPetsSearchScreenState(
                state = CONTENT,
                items = items,
                petNameFilter = state.petNameFilter
            )
        }
    }

    fun petNameFilterChanged(newName: String) {
        emitState(
            state.copy(
                petNameFilter = newName,
                state = LOADING,
            )
        )

        emitStateAsync(
            onError = {
                state.copy(state = ERROR)
            }
        ) {
            val items = repository.getPets(name = newName)
            state.copy(
                state = CONTENT,
                items = items
            )
        }
    }

    fun openPetInfo(pet: PetEntity) {
        navigate(VetScreen.PetInfoScreen, mapOf(PetInfoScreen.PET_ID_KEY to pet.id))
    }

    fun popBack() {
        navHostController?.popBackStack()
    }
}