package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisEntity(
    @SerialName("diagnosis_id")
    val diagnosisId:Int,
    @SerialName("diagnosis_name")
    val diagnosisName: String,
    @SerialName("diagnosis_type")
    val diagnosisType: DiagloseType
) {
    companion object {
        fun generate() = DiagnosisEntity(
            diagnosisId = 1,
            diagnosisName = "Эпилепсия",
            diagnosisType = DiagloseType.generate()
        )
    }
}

@Serializable
data class DiagloseType(
    val id: Int,
    val value: String
) {
    companion object {
        fun generate() = DiagloseType(
            id = 1,
            value = "Very scary diagnosis"
        )
    }
}
