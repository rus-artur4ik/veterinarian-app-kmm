package com.rus_artur4ik.veterinarian.medcard

import com.rus_artur4ik.veterinarian.common.mvvm.CoreViewModel
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class MedCardViewModel: CoreViewModel<MedCardScreenState>() {

    override fun provideInitialScreenState(): MedCardScreenState {
        return MedCardScreenState(
            petNameFilter = "",
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
            )
        )
    }

    fun petNameFilterChanged(newName: String) {
        emitState(
            state.value.copy(petNameFilter = newName)
        )
    }
}