package com.rus_artur4ik.veterinarian.data

import com.rus_artur4ik.veterinarian.data.dto.AccessTokenDto
import com.rus_artur4ik.veterinarian.data.dto.AppointmentDto
import com.rus_artur4ik.veterinarian.data.dto.AuthDto
import com.rus_artur4ik.veterinarian.data.dto.RefreshTokenDto
import com.rus_artur4ik.veterinarian.data.dto.TokensDto
import com.rus_artur4ik.veterinarian.data.exception.UnauthorizedException
import com.rus_artur4ik.veterinarian.data.exception.WrongCredentialsException
import com.rus_artur4ik.veterinarian.data.preference.SharedPreferenceContext
import com.rus_artur4ik.veterinarian.data.preference.TokenStorage
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json

internal class VetApi(sharedPreferenceContext: SharedPreferenceContext) {

    private val tokenStorage = TokenStorage(sharedPreferenceContext)

    private val json = Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = true
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
    }

    private val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(message, tag = "HTTP Client")
                    }
                }
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = tokenStorage.get()
                        if (token.accessToken.isEmpty()) throw UnauthorizedException()
                        token
                    }
                    refreshTokens {
                        val oldTokens = tokenStorage.get()
                        val newToken = refresh(oldTokens.refreshToken)
                        val newTokens = BearerTokens(newToken.accessToken, oldTokens.refreshToken)
                        tokenStorage.put(newTokens)
                        newTokens
                    }
                    sendWithoutRequest {
                        !it.url.pathSegments.contains("auth")
                    }
                }
            }
        }
    }

    suspend fun auth(email: String, password: String): BearerTokens {
        val response = client.post("$BASE_URL/api/v5/auth/authenticate") {
            contentType(ContentType.Application.Json)
            setBody(AuthDto(email,password))
        }
        if (response.status == HttpStatusCode.Forbidden) throw WrongCredentialsException()

        return response.body<TokensDto>().toBearerTokens()
    }

    suspend fun refresh(refreshToken: String): AccessTokenDto {
        return makePostRequest("/api/v5/auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(RefreshTokenDto(refreshToken))
        }
    }

    suspend fun getPets(
        limit: Int?,
        kindName: String?,
        name: String?
    ): List<PetEntity> {
        return makeGetRequest("/api/v1/pets") {
            limit?.let { parameter("max_count", it) }
            kindName?.let { parameter("kind", it) }
            name?.let { parameter("name", it) }
        }
    }

    suspend fun getPet(id: Int): PetEntity {
        return makeGetRequest("/api/v1/get_pet") {
            parameter("pet_id", id)
        }
    }

    suspend fun getVisits(
        limit: Int? = null,
        petId: Int? = null,
        breedId: Int? = null,
        kindId: Int? = null,
        dateFrom: LocalDateTime? = null,
        dateTo: LocalDateTime? = null
    ): List<VisitEntity> {
        return makeGetRequest("/api/v2/visits") {
            limit?.let { parameter("max_count", it) }
            petId?.let { parameter("pet_id", it) }
            breedId?.let { parameter("breed_id", it) }
            kindId?.let { parameter("kind_id", it) }
            dateFrom?.let { parameter("date1", it) }
            dateTo?.let { parameter("date2", it) }
        }
    }
    
    suspend fun getVisit(id: Int): VisitEntity {
        return makeGetRequest("/api/v4/visit") {
            parameter("visit_id", id)
        }
    }

    suspend fun getAppointments(limit: Int?): List<AppointmentDto> {
        return makeGetRequest("/api/v2/appointments") {
            limit?.let { parameter("max_count", it) }
        }
    }

    suspend fun getProfiles(): ProfileEntity {
        return makeGetRequest("/api/v2/client_info")
    }

    private suspend inline fun <reified T> makeGetRequest(path: String, block: HttpRequestBuilder.() -> Unit = {}): T {
        val response = client.get("$BASE_URL$path") { block() }

        if (response.status == HttpStatusCode.Unauthorized) throw UnauthorizedException()
        return response.body()
    }

    private suspend inline fun <reified T> makePostRequest(path: String, block: HttpRequestBuilder.() -> Unit = {}): T {
        val response = client.post("$BASE_URL$path") { block() }

        if (response.status == HttpStatusCode.Unauthorized) throw UnauthorizedException()
        return response.body()
    }

    companion object {
        private const val BASE_URL = "http://45.141.79.132:8090"
    }
}