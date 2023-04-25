package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedEntity(
    @SerialName("breed_id")
    val id: Int,
    @SerialName("breed_name")
    val name: String
) {
    companion object {
        fun generate() = BreedEntity(
            id = 1,
            name = "Немецкая овчарка"
        )
    }
}