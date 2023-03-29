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
)