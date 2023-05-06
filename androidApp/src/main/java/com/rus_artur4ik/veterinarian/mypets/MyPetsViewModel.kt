package com.rus_artur4ik.veterinarian.mypets

import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.VetScreen.MyPetsSearchScreen
import com.rus_artur4ik.veterinarian.VetScreen.PetInfoScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

class MyPetsViewModel : BaseEmptyableViewModel<MyPetsScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    init {
        emitStateAsync {
            MyPetsScreenState(repository.getPets())
        }
    }

    override fun isContentEmpty(content: MyPetsScreenState): Boolean {
        return content.items.isEmpty()
    }

    override fun provideInitialScreenState(): LceState<MyPetsScreenState> {
        return LceState.Loading()
    }

    fun openPetInfo(pet: PetEntity) {
        navigate(PetInfoScreen) //todo
    }

    fun goToSearchMode() {
        navigate(MyPetsSearchScreen)
    }
}

