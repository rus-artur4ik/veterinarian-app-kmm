package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableTimesEntity(
    @SerialName("free_time")
    val freeTimes: List<LocalDateTime>
)