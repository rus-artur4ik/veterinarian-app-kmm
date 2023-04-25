package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VisitEntity(
    @SerialName("visit_id")
    val id: Int,
    val date: LocalDateTime,
    val diagnoses: List<DiagnoseEntity>,
    val pet: PetEntity,
) {
    companion object {
        fun generate() = VisitEntity(
            id = 1,
            date = LocalDateTime(2021, 1, 12, 10, 32),
            diagnoses = listOf(DiagnoseEntity.generate(), DiagnoseEntity.generate()),
            pet = PetEntity.generate()
        )
    }
}