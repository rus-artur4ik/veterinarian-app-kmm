package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.BreedEntity
import com.rus_artur4ik.veterinarian.domain.entity.DiagnoseEntity
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.SexEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class PetInfoViewModel: MvvmViewModel<PetInfoScreenState>() {

    override fun provideInitialScreenState(): PetInfoScreenState {
        return PetInfoScreenState(
            pet = PetEntity(
                id = 1,
                name = "Кеша",
                breed = BreedEntity(1, "Ориентальная кошка"),
                sex = SexEntity(1, "Самец"),
                birthday = null,
                kind = KindEntity(1, "Кот"),
                sterilized = false
            ),
            closestAppointment = AppointmentEntity(
                doctor = "Ай-Болит",
                date = LocalDateTime(2001, 1, 24, 12, 24),
                type = "Тип приема",
                pet = PetEntity(
                    id = 1,
                    name = "Кеша",
                    breed = BreedEntity(1, "Ориентальная кошка"),
                    sex = SexEntity(1, "Самец"),
                    birthday = null,
                    kind = KindEntity(1, "Кот"),
                    sterilized = false
                )
            ),
            visits = listOf(
                VisitEntity(
                    id = 1,
                    date = LocalDateTime(
                        LocalDate.fromEpochDays(10),
                        LocalTime(1, 1, 1)
                    ),
                    diagnoses = listOf(
                        DiagnoseEntity(1, "Диагноз 1"),
                        DiagnoseEntity(2, "Диагноз 2")
                    ),
                    pet = PetEntity(
                        id = 1,
                        name = "Кеша",
                        breed = BreedEntity(1, "Ориентальная кошка"),
                        sex = SexEntity(1, "Самец"),
                        birthday = null,
                        kind = KindEntity(1, "Кот"),
                        sterilized = false
                    )
                ),
                VisitEntity(
                    id = 1,
                    date = LocalDateTime(
                        LocalDate.fromEpochDays(34),
                        LocalTime(1, 1, 1)),
                    diagnoses = listOf(
                        DiagnoseEntity(1, "Диагноз 1"),
                        DiagnoseEntity(2, "Диагноз 2")
                    ),
                    pet = PetEntity(
                        id = 1,
                        name = "Кеша",
                        breed = BreedEntity(1, "Ориентальная кошка"),
                        sex = SexEntity(1, "Самец"),
                        birthday = null,
                        kind = KindEntity(1, "Кот"),
                        sterilized = false
                    )
                )
            )
        )
    }

    fun showAllVisits() {
        //TODO
    }
}