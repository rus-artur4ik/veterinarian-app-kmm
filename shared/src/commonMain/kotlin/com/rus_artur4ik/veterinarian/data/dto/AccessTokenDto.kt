package com.rus_artur4ik.veterinarian.data.dto

import kotlinx.serialization.SerialName

@Serializable
data class AccessTokenDto(
    @SerialName("access_token")
    val accessToken: String
)