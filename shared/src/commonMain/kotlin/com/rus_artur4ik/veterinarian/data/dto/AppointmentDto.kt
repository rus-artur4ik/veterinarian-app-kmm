package com.rus_artur4ik.veterinarian.data.dto

import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentDto(
    val appointment_id: Int,
    val doctor_name: String? = null,
    val description: String,
    val date: LocalDateTime,
    val type: String,
    val pet: PetEntity
)