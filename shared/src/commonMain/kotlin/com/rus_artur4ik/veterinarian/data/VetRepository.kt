package com.rus_artur4ik.veterinarian.data

import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDateTime

class VetRepository {

    suspend fun getPets(
        limit: Int? = null,
        kindName: String? = null
    ): List<PetEntity> {
        return VetApi.getPets(limit, kindName)
    }

    suspend fun getVisits(
        limit: Int? = null,
        breedId: Int? = null,
        kindId: Int? = null,
        dateFrom: LocalDateTime? = null,
        dateTo: LocalDateTime? = null
    ): List<VisitEntity> {
        return VetApi.getVisits(limit, breedId, kindId, dateFrom, dateTo)
    }
}