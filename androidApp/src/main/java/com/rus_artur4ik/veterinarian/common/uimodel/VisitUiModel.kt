package com.rus_artur4ik.veterinarian.common.uimodel

import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VaccineEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitType
import kotlinx.datetime.LocalDateTime

data class VisitUiModel(
    val id: Int,
    val temperature: String? = null,
    val heartBeat: String? = null,
    val breathBeat: String? = null,
    val weight: String? = null,
    val vaccine: VaccineEntity? = null,
    val nextVaccination: LocalDateTime? = null,
    val anamnesis: String? = null,
    val prescription: String? = null,
    val recommendation: String? = null,
    val date: LocalDateTime,
    val diagnoses: List<DiagnosisUiModel>? = null,
    val type: VisitType,
    val firstVisitId: Int? = null,
    val pet: PetEntity,
) {

    fun toVisitEntity(): VisitEntity {
        return VisitEntity(
            id,
            temperature,
            heartBeat,
            breathBeat,
            weight,
            vaccine,
            nextVaccination,
            anamnesis,
            prescription,
            recommendation,
            date,
            diagnoses?.map { it.diagnosis },
            type,
            firstVisitId,
            pet
        )
    }

    companion object {
        fun from(visitEntity: VisitEntity, expandedDiagnosisIds: List<Int>): VisitUiModel {
            return with (visitEntity) {
                VisitUiModel(
                    id,
                    temperature,
                    heartBeat,
                    breathBeat,
                    weight,
                    vaccine,
                    nextVaccination,
                    anamnesis,
                    prescription,
                    recommendation,
                    date,
                    diagnoses?.map {
                        DiagnosisUiModel(it, expandedDiagnosisIds.contains(it.diagnosisId))
                    },
                    type,
                    firstVisitId,
                    pet
                )
            }
        }
    }
}