package com.rus_artur4ik.veterinarian.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthDto(
    val email: String,
    val password: String
)