package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SexEntity(
    @SerialName("sex_id")
    val id: Int,
    @SerialName("sex_name")
    val name: String
) {
    companion object {
        fun generate() = SexEntity(
            id = 1,
            name = "male"
        )
    }
}