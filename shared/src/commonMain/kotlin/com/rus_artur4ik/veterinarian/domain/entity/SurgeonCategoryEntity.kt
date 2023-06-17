package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SurgeonCategoryEntity(
    val id: Int,
    val value: String
)
