package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.veterinarian.common.mvvm.CoreViewModel
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class PetInfoViewModel: CoreViewModel<PetInfoScreenState>() {

    override fun provideInitialScreenState(): PetInfoScreenState {
        return PetInfoScreenState(
            pet = PetEntity(
                name = "Кеша",
                breed = "Ориентальная кошка",
                sex = Sex.MALE,
                birthday = null,
                kind = "Кот",
                lastVisit = null
            ),
            closestAppointment = AppointmentEntity(
                doctor = "Ай-Болит",
                date = LocalDateTime(2001, 1, 24, 12, 24),
                type = "Тип приема",
                pet = PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                )
            ),
            visits = listOf(
                VisitEntity(
                    date = LocalDateTime(
                        LocalDate.fromEpochDays(10),
                        LocalTime(1, 1, 1)
                    ),
                    diagnoses = listOf(
                        "Диагноз 1",
                        "Диагноз 2",
                    ),
                    pet = PetEntity(
                        name = "Кеша",
                        breed = "Ориентальная кошка",
                        sex = Sex.MALE,
                        birthday = null,
                        kind = "Кот",
                        lastVisit = null
                    )
                ),
                VisitEntity(
                    date = LocalDateTime(
                        LocalDate.fromEpochDays(34),
                        LocalTime(1, 1, 1)),
                    diagnoses = listOf(
                        "Диагноз 1",
                        "Диагноз 2",
                    ),
                    pet = PetEntity(
                        name = "Кеша",
                        breed = "Ориентальная кошка",
                        sex = Sex.MALE,
                        birthday = null,
                        kind = "Кот",
                        lastVisit = null
                    )
                )
            )
        )
    }

    fun showAllVisits() {
        //TODO
    }
}