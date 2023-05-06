package com.rus_artur4ik.veterinarian.home

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

data class HomeScreenState(
    val pets: List<PetEntity>,
    val visits: List<VisitEntity>,
    val profile: ProfileEntity,
    val closestAppointment: AppointmentEntity?,
    val hasUnreadNotifications: Boolean
) : MvvmState
