package com.rus_artur4ik.veterinarian.home

import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.coroutines.async

class HomeViewModel : LceViewModel<HomeScreenState>() {

    private val repository = VetRepository()

    init {
        val pets = viewModelScope.async { repository.getPets() }
        val visit = viewModelScope.async { repository.getVisits(limit = 1) }

        emitStateAsync {
            HomeScreenState(
                pets = pets.await(),
                lastVisit = visit.await().firstOrNull()
            )
        }
    }

    override fun provideInitialScreenState(): LceState<HomeScreenState> {
        return LceState.Loading()
    }

    fun makeAnAppointment() {
        //TODO
    }

    fun notifyAboutVisit() {
        //TODO
    }

    fun goToPetInfo(pet: PetEntity) {
        //TODO
    }
}