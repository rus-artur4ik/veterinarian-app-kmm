package com.rus_artur4ik.veterinarian.data

import com.rus_artur4ik.veterinarian.data.mapper.AppointmentMapper
import com.rus_artur4ik.veterinarian.data.preference.SharedPreferenceContext
import com.rus_artur4ik.veterinarian.data.preference.TokenStorage
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType
import com.rus_artur4ik.veterinarian.domain.entity.AvailableTimesEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.SurgeonEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import io.ktor.client.plugins.auth.providers.BearerTokens
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atTime

class VetRepository(sharedPreferenceContext: SharedPreferenceContext) {

    private val api = VetApi(sharedPreferenceContext)
    val tokenStorage = TokenStorage(sharedPreferenceContext)

    suspend fun auth(email: String, password: String) {
        val tokens = api.auth(email, password)
        tokenStorage.put(tokens)
    }

    fun signOut() {
        tokenStorage.put(BearerTokens("", ""))
    }

    // -------------------------------

    suspend fun getPets(
        limit: Int? = null,
        kindName: String? = null,
        name: String? = null
    ): List<PetEntity> {
        return api.getPets(limit, kindName, name)
    }

    suspend fun getPet(id: Int): PetEntity {
        return api.getPet(id)
    }

    suspend fun getVisits(
        limit: Int? = null,
        petId: Int? = null,
        breedId: Int? = null,
        kindId: Int? = null,
        dateFrom: LocalDateTime? = null,
        dateTo: LocalDateTime? = null
    ): List<VisitEntity> {
        return api.getVisits(limit, petId, breedId, kindId, dateFrom, dateTo)
    }

    suspend fun getVisit(id: Int): VisitEntity {
        return api.getVisit(id)
    }

    suspend fun getAppointments(limit: Int? = null): List<AppointmentEntity> {
        return api.getAppointments(limit).map { AppointmentMapper.map(it) }
    }

    suspend fun getProfile(): ProfileEntity {
        return api.getProfiles()
    }

    suspend fun getSurgeons(name: String): List<SurgeonEntity> {
        return api.getSurgeons(name)
    }

    suspend fun getAvailableTime(date: LocalDate): AvailableTimesEntity {
        return api.getAvailableTime(0, date.atTime(12,0,0)) // TODO добавить айди доктора, когда бэк поддержит
    }

    suspend fun postMakeAppointment(
        petId: Int?,
        date: LocalDateTime,
        surgeonId: Int,
        type: AppointmentType
    ) {
        // Hardcoded id is id of "new pet" pet
        api.postMakeAppointment(petId ?: 104327, date, surgeonId, type)
    }
}