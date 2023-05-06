package com.rus_artur4ik.veterinarian.data.dto

import kotlinx.serialization.SerialName

@Serializable
data class RefreshTokenDto(
    @SerialName("refresh_token")
    val refreshToken: String
)