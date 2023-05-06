package com.rus_artur4ik.veterinarian.data.dto

@Serializable
data class AuthDto(
    val email: String,
    val password: String
)