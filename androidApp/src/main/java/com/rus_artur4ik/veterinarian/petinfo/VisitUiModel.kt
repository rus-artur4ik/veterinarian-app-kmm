package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitType
import kotlinx.datetime.LocalDateTime

data class VisitUiModel(
    val id: Int,
    val date: LocalDateTime,
    val diagnoses: List<DiagnosisUiModel>? = null,
    val type: VisitType,
    val firstVisitId: Int? = null,
    val pet: PetEntity,
) {

    fun toVisitEntity(): VisitEntity {
        return VisitEntity(id, date, diagnoses?.map { it.diagnosis }, type, firstVisitId, pet)
    }

    companion object {
        fun from(visitEntity: VisitEntity, expandedDiagnosisIds: List<Int>): VisitUiModel {
            return VisitUiModel(
                id = visitEntity.id,
                date = visitEntity.date,
                diagnoses = visitEntity.diagnoses?.map {
                    DiagnosisUiModel(it, expandedDiagnosisIds.contains(it.diagnosisId))
                },
                type = visitEntity.type,
                firstVisitId = visitEntity.firstVisitId,
                pet = visitEntity.pet
            )
        }
    }
}