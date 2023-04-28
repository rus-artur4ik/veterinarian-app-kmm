package com.rus_artur4ik.veterinarian.data

import com.rus_artur4ik.veterinarian.data.dto.AppointmentDto
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDateTime

internal object VetApi {

    private const val BASE_URL = "http://45.141.79.132:8090"

    private val client by lazy {
        HttpClient() {
            install(ContentNegotiation) { json() }
            install(Logging)
        }
    }

    suspend fun getPets(
        limit: Int?,
        kindName: String?
    ): List<PetEntity> {
        return client.get("$BASE_URL/api/v1/pets") {
            limit?.let { parameter("max_count", it) }
            kindName?.let { parameter("kind", it) }
        }.body()
    }

    suspend fun getVisits(
        limit: Int? = null,
        breedId: Int? = null,
        kindId: Int? = null,
        dateFrom: LocalDateTime? = null,
        dateTo: LocalDateTime? = null
    ): List<VisitEntity> {
        return client.get("$BASE_URL/api/v2/visits") {
            limit?.let { parameter("max_count", it) }
            breedId?.let { parameter("breed_id", it) }
            kindId?.let { parameter("kind_id", it) }
            dateFrom?.let { parameter("date1", it) } //TODO
            dateTo?.let { parameter("date2", it) } //TODO
        }.body()
    }

    suspend fun getAppointments(limit: Int?): List<AppointmentDto> {
        return client.get("$BASE_URL/api/v2/appointments") {
            limit?.let { parameter("max_count", it) }
        }.body()
    }

    suspend fun getProfiles(): ProfileEntity {
        return client.get("$BASE_URL/api/v2/client_info").body()
    }
}