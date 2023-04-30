package com.rus_artur4ik.veterinarian.data.dto

import io.ktor.client.plugins.auth.providers.BearerTokens
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
) {
    fun toBearerTokens(): BearerTokens {
        return BearerTokens(accessToken, refreshToken)
    }
}