package com.rus_artur4ik.veterinarian.data.preference

import io.ktor.client.plugins.auth.providers.BearerTokens

class TokenStorage(private val context: SharedPreferenceContext) {

    fun put(bearerTokens: BearerTokens) {
        context.putString(ACCESS_TOKEN, bearerTokens.accessToken)
        context.putString(REFRESH_TOKEN, bearerTokens.refreshToken)
    }

    fun get(): BearerTokens {
        return BearerTokens(
            accessToken = context.getString(ACCESS_TOKEN) ?: "",
            refreshToken = context.getString(REFRESH_TOKEN) ?: ""
        )
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}