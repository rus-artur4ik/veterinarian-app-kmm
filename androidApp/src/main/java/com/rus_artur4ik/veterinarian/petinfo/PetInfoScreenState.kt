package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

data class PetInfoScreenState(
    val pet: PetEntity,
    val closestAppointment: AppointmentEntity,
    val visits: List<VisitEntity>
): MvvmState