package com.rus_artur4ik.veterinarian.data.dto

import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.datetime.LocalDateTime

@Serializable
data class AppointmentDto(
    val appointment_id: Int,
    val doctor_name: String? = null,
    val description: String = "Appointment description", //TODO
    val date: LocalDateTime,
    val type: String,
    val pet: PetEntity
)