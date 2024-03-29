package com.rus_artur4ik.veterinarian.domain.entity

import com.rus_artur4ik.veterinarian.domain.entity.VisitType.SECONDARY
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VisitEntity(
    @SerialName("visit_id")
    val id: Int,
    val temperature: String? = null,
    @SerialName("heart_beat")
    val heartBeat: String? = null,
    @SerialName("breath_beat")
    val breathBeat: String? = null,
    val weight: String? = null,
    val vaccine: VaccineEntity? = null,
    @SerialName("next_vaccination")
    val nextVaccination: LocalDateTime? = null,
    val anamnesis: String? = null,
    val prescription: String? = null,
    val recommendation: String? = null,
    val date: LocalDateTime,
    val diagnoses: List<DiagnosisEntity>? = null,
    val type: VisitType,
    @SerialName("first_visit_id")
    val firstVisitId: Int? = null,
    val pet: PetEntity,
) {
    companion object {
        fun generate() = VisitEntity(
            id = 1,
            date = LocalDateTime(2021, 1, 12, 10, 32),
            diagnoses = listOf(DiagnosisEntity.generate(), DiagnosisEntity.generate()),
            type = SECONDARY,
            firstVisitId = 1,
            pet = PetEntity.generate()
        )
    }
}

@Serializable
enum class VisitType {
    FIRST, SECONDARY, VACCINATION
}