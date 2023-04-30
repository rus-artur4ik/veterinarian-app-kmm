package com.rus_artur4ik.veterinarian.profile

import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceViewModel
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ProfileViewModel: LceViewModel<ProfileScreenState>() {

    val repository = VetRepository(AppContextHolder.context)

    init {
        val profile = viewModelScope.async(Dispatchers.IO) {
            repository.getProfile()
        }
        val appointments = viewModelScope.async(Dispatchers.IO) {
            repository.getAppointments()
        }

        emitStateAsync {
            ProfileScreenState(
                profile.await(),
                appointments.await()
            )
        }
    }

    fun goToAllAppointments() {
        // TODO
    }

    fun goToClinicContacts() {
        //TODO
    }

    fun goToUserAgreement() {
        //TODO
    }

    fun goToPersonalDataAgreement() {
        //TODO
    }

    fun openAppointmentMenu(appointmentEntity: AppointmentEntity) {
        //TODO
    }

    fun closeMenu() {
        // TODO
    }
}