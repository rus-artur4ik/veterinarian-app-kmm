package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime

data class VisitEntity(
    val date: LocalDateTime,
    val diagnoses: List<String>,
    val pet: PetEntity,
)