package com.rus_artur4ik.veterinarian.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileEntity(
    @SerialName("client_id")
    val id: Int,
    val name: String,
    @SerialName("middle_name")
    val middleName: String? = null,
    val surname: String,
    val phone: String? = null,
    val email: String? = null
) {
    companion object {
        fun generate() = ProfileEntity(
            id = 1,
            name = "Ivan",
            middleName = "Ivanovich",
            surname = "Ivanov",
            phone = "+79123346465",
            email = "ivanovivan@gmail.com"
        )
    }
}
