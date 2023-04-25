package com.rus_artur4ik.veterinarian.domain.entity

import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType.ULTRASOUND
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentEntity(
    @SerialName("appointment_id")
    val id: Int,
    @SerialName("doctor_name")
    val doctorName: String? = null,
    val description: String = "Appointment description",
    val date: LocalDateTime,
    val type: AppointmentType,
    val pet: PetEntity
) {
    companion object {
        fun generate() = AppointmentEntity(
            id = 1,
            doctorName = "Aibolit",
            date = LocalDateTime(2001, 1, 24, 12, 55),
            description = "Appointment description",
            type = ULTRASOUND,
            pet = PetEntity.generate()
        )
    }
}

enum class AppointmentType {
    VISIT, ULTRASOUND, SURGEON
}
