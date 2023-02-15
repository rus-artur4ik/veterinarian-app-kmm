package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime

data class AppointmentEntity(
    val doctor: String,
    val date: LocalDateTime,
    val type: String,
    val pet: PetEntity
)
