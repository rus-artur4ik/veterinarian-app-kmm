package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

class PetInfoViewModel: MvvmViewModel<PetInfoScreenState>() {

    override fun provideInitialScreenState(): PetInfoScreenState {
        return PetInfoScreenState(
            pet = PetEntity.generate(),
            closestAppointment = AppointmentEntity.generate(),
            visits = listOf(
                VisitEntity.generate(),
                VisitEntity.generate()
            )
        )
    }

    fun showAllVisits() {
        //TODO
    }
}