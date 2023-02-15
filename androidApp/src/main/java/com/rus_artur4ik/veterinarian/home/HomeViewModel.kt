package com.rus_artur4ik.veterinarian.home

import com.rus_artur4ik.petcore.mvvm.CoreViewModel
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class HomeViewModel : CoreViewModel<HomeScreenState>() {

    override fun provideInitialScreenState(): HomeScreenState {
        return HomeScreenState(
            pets = listOf(
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
                PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                ),
            ),
            lastVisit = VisitEntity(
                date = LocalDateTime(
                    LocalDate.fromEpochDays(10),
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
    }

    fun makeAnAppointment() {
        //TODO
    }

    fun notifyAboutVisit() {
        //TODO
    }

    fun goToPetInfo(pet: PetEntity) {
        //TODO
    }
}