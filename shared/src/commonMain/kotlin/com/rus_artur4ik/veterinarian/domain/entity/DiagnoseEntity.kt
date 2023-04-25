package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnoseEntity(
    @SerialName("diagnosis_id")
    val diagnoseId:Int,
    @SerialName("diagnosis_name")
    val diagnoseName: String
) {
    companion object {
        fun generate() = DiagnoseEntity(
            diagnoseId = 1,
            diagnoseName = "Эпилепсия"
        )
    }
}
