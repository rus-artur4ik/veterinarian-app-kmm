package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ColorEntity(
    @SerialName("color_id")
    val id: Int,
    @SerialName("color_name")
    val name: String
) {
    companion object {
        fun generate() = ColorEntity(
            id = 1,
            name = "красный"
        )
    }
}