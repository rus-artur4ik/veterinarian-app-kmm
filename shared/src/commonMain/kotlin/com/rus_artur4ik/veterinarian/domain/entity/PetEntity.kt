package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetEntity(
    @SerialName("pet_id")
    val id: Int,
    val name: String,
    val kind: KindEntity,
    val breed: BreedEntity? = null,
    val sex: SexEntity? = null,
    val birthday: LocalDateTime? = null,
    val sterilized: Boolean,
    val color: ColorEntity? = null
)
