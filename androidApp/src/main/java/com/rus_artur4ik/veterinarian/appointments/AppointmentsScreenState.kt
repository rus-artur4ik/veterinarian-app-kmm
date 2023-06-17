package com.rus_artur4ik.veterinarian.appointments

import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity

data class AppointmentsScreenState(
    val appointments: List<AppointmentEntity>
)