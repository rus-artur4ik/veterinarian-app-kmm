package com.rus_artur4ik.veterinarian.home

import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.petcore.mvvm.lce.LceViewModel
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.coroutines.async

class HomeViewModel : LceViewModel<HomeScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    init {
        val pets = viewModelScope.async { repository.getPets() }
        val visit = viewModelScope.async { repository.getVisits(limit = 3) }
        val profile = viewModelScope.async { repository.getProfile() }
        val closestAppointment = viewModelScope.async { repository.getAppointments(limit = 1).firstOrNull() }

        emitStateAsync {
            HomeScreenState(
                pets = pets.await(),
                visits = visit.await(),
                profile = profile.await(),
                closestAppointment = closestAppointment.await()
            )
        }
    }

    override fun provideInitialScreenState(): LceState<HomeScreenState> {
        return LceState.Loading()
    }

    fun goToPetInfo(pet: PetEntity) {
        //TODO
    }

    fun onDetailedClick(appointmentEntity: AppointmentEntity) {
        //TODO
    }
}