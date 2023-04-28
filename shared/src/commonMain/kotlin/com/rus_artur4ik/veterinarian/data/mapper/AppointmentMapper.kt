package com.rus_artur4ik.veterinarian.data.mapper

import com.rus_artur4ik.veterinarian.data.dto.AppointmentDto
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType.SURGEON
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType.ULTRASOUND
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType.VISIT

object AppointmentMapper {

    fun map(dto: AppointmentDto) = AppointmentEntity(
        id = dto.appointment_id,
        doctorName = dto.doctor_name,
        description = dto.description,
        date = dto.date,
        type = when (dto.type) {
            "VISIT" -> VISIT
            "ULTRASOUND" -> ULTRASOUND
            "SURGEON" -> SURGEON
            else -> VISIT
        },
        pet = dto.pet
    )
}