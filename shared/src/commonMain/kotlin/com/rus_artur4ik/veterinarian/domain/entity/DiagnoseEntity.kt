package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnoseEntity(
    @SerialName("diagnosis_id")
    val diagnoseId:Int,
    @SerialName("diagnosis_name")
    val diagnoseName: String,
    @SerialName("diagnosis_type")
    val diagnoseType: DiagloseType
) {
    companion object {
        fun generate() = DiagnoseEntity(
            diagnoseId = 1,
            diagnoseName = "Эпилепсия",
            diagnoseType = DiagloseType.generate()
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
