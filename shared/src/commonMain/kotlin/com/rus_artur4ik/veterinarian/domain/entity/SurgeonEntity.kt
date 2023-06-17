package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SurgeonEntity(
    val id: Int,
    val category: SurgeonCategoryEntity,
    val name: String
)