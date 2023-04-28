package com.rus_artur4ik.veterinarian.profile

import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity

data class ProfileScreenState(
    val profile: ProfileEntity,
    val appointments: List<AppointmentEntity>
): MvvmState