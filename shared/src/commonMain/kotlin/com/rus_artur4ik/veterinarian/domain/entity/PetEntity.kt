package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime

data class PetEntity(
    val name: String,
    val breed: String,
    val sex: Sex,
    val birthday: LocalDateTime?,
    val kind: String,
    val lastVisit: LocalDateTime?
)

enum class Sex {
    MALE, FEMALE
}
