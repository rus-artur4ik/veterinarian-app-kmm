package com.rus_artur4ik.veterinarian.data

import com.rus_artur4ik.veterinarian.data.mapper.AppointmentMapper
import com.rus_artur4ik.veterinarian.data.preference.SharedPreferenceContext
import com.rus_artur4ik.veterinarian.data.preference.TokenStorage
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDateTime

class VetRepository(sharedPreferenceContext: SharedPreferenceContext) {

    private val api = VetApi(sharedPreferenceContext)
    val tokenStorage = TokenStorage(sharedPreferenceContext)

    suspend fun auth(email: String, password: String) {
        val tokens = api.auth(email, password)
        tokenStorage.put(tokens)
    }

    // -------------------------------

    suspend fun getPets(
        limit: Int? = null,
        kindName: String? = null
    ): List<PetEntity> {
        return api.getPets(limit, kindName)
    }

    suspend fun getVisits(
        limit: Int? = null,
        breedId: Int? = null,
        kindId: Int? = null,
        dateFrom: LocalDateTime? = null,
        dateTo: LocalDateTime? = null
    ): List<VisitEntity> {
        return api.getVisits(limit, breedId, kindId, dateFrom, dateTo)
    }

    suspend fun getAppointments(limit: Int? = null): List<AppointmentEntity> {
        return api.getAppointments(limit).map { AppointmentMapper.map(it) }
    }

    suspend fun getProfile(): ProfileEntity {
        return api.getProfiles()
    }
}