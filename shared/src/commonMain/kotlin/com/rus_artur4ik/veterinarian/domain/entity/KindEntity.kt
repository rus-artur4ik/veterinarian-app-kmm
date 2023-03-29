package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KindEntity(
    @SerialName("kind_id")
    val id: Int,
    @SerialName("kind_name")
    val name: String
)