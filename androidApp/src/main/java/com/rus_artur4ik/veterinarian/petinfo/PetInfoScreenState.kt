package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

data class PetInfoScreenState(
    val pet: PetEntity,
    val closestAppointment: AppointmentEntity?,
    val visits: List<VisitUiModel>
): MvvmState