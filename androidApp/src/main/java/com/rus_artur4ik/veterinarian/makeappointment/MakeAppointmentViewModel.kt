package com.rus_artur4ik.veterinarian.makeappointment

import android.widget.Toast
import com.rus_artur4ik.petcore.mvvm.MvvmViewModel
import com.rus_artur4ik.petcore.mvvm.lce.Lce
import com.rus_artur4ik.petcore.mvvm.lce.LceeState
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.AvailableTimesEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.SurgeonCategoryEntity
import com.rus_artur4ik.veterinarian.domain.entity.SurgeonEntity
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage1
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage2
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage3
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage4
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage5
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage6
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.SuccessScreen
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime

class MakeAppointmentViewModel : MvvmViewModel<MakeAppointmentScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    override fun provideInitialScreenState(): MakeAppointmentScreenState {
        return MakeAppointmentScreenState(
            Stage1()
        )
    }

    fun onStage1ServiceClick(item: Stage.Service) {
        when (item) {
            Stage.Service.ULTRASOUND -> {
                val stage2 = Stage2(Lce.Loading, Stage.Service.ULTRASOUND, items = listOf(), "")
                goToStage3(
                    stage2,
                    SurgeonEntity(
                        id = 57,
                        category = SurgeonCategoryEntity(1, resources.getString(R.string.ultrasound)),
                        name = resources.getString(R.string.ultrasound)
                    )
                )
            }
            Stage.Service.SURGEON -> goToStage2(item)
        }

    }

    private fun goToStage2(item: Stage.Service) {
        emitState(
            MakeAppointmentScreenState(
                Stage2(
                    lce = Lce.Loading,
                    service = item,
                    items = listOf(),
                    name = ""
                )
            )
        )

        emitStateAsync(
            onError = { exception ->
                MakeAppointmentScreenState(
                    Stage2(
                        lce = Lce.Error(exception),
                        service = item,
                        items = listOf(),
                        name = ""
                    )
                )
            },
            state = {
                MakeAppointmentScreenState(
                    Stage2(
                        lce = Lce.Content,
                        service = item,
                        items = repository.getSurgeons(""),
                        name = ""
                    )
                )
            }
        )
    }

    fun changeServiceSearchQuery(name: String) {
        val oldStage = state.stage as? Stage2 ?: return

        emitState(
            state.copy(
                stage = oldStage.copy(
                    lce = Lce.Loading,
                    items = listOf(),
                    name = name
                )
            )
        )

        emitStateAsync(
            onError = { exception ->
                state.copy(
                    stage = oldStage.copy(
                        lce = Lce.Error(exception),
                        name = name
                    )
                )
            },
            state = {
                state.copy(
                    stage = oldStage.copy(
                        lce = Lce.Content,
                        name = name,
                        items = repository.getSurgeons(name)
                    )
                )
            }
        )
    }

    fun onStage2ServiceClick(item: SurgeonEntity) {
        val stage2State = state.stage as? Stage2 ?: return
        goToStage3(stage2State, item)
    }

    private fun goToStage3(stage: Stage2, item: SurgeonEntity) {
        emitState(
            MakeAppointmentScreenState(
                Stage3(
                    lce = Lce.Loading,
                    stage2State = stage,
                    surgeonEntity = item,
                    date = null,
                    time = null,
                    availableTimes = AvailableTimesEntity(listOf()),
                    isCalendarExpanded = false
                )
            )
        )
    }

    fun expandCalendar(expand: Boolean, selectedDate: LocalDate?) {
        val stage = state.stage as? Stage3 ?: return
        emitState(
            state.copy(
                stage = stage.copy(
                    isCalendarExpanded = expand,
                    lce = Lce.Loading,
                    date = selectedDate
                )
            )
        )

        if (!expand && selectedDate != null) {
            emitStateAsync(
                onError = {
                    state.copy(
                        stage = stage.copy(
                            lce = Lce.Error(it),
                            isCalendarExpanded = false,
                            availableTimes = AvailableTimesEntity(listOf()),
                            date = selectedDate
                        )
                    )
                }
            ) {
                state.copy(
                    stage = stage.copy(
                        lce = Lce.Content,
                        isCalendarExpanded = false,
                        availableTimes = repository.getAvailableTime(selectedDate),
                        date = selectedDate
                    )
                )
            }
        }
    }

    fun setTime(time: LocalTime) {
        val stage = state.stage as? Stage3 ?: return
        emitState(
            state.copy(
                stage = stage.copy(
                    time = time
                )
            )
        )
    }

    fun goToStage4() {
        val stage = (state.stage as? Stage3) ?: return
        emitState(
            MakeAppointmentScreenState(
                Stage4(stage)
            )
        )
    }

    fun selectNewPet() {
        val stage3 = (state.stage as? Stage4)?.stage3State ?: return
        goToStage6(stage3, null)
    }

    fun selectExistingPet() {
        val stage3 = (state.stage as? Stage4)?.stage3State ?: return
        goToStage5(stage3)
    }

    fun goToStage5(stage3: Stage3) {
        val stage = Stage5(
            stage3State = stage3,
            lcee = LceeState.Lcee.Loading,
            petNameFilter = "",
            pets = listOf()
        )

        emitState(
            MakeAppointmentScreenState(stage)
        )

        emitStateAsync(
            onError = {
                MakeAppointmentScreenState(
                    stage.copy(lcee = LceeState.Lcee.Error(it))
                )
            }
        ) {
            MakeAppointmentScreenState(
                stage.copy(
                    lcee = LceeState.Lcee.Content,
                    pets = repository.getPets(name = stage.petNameFilter)
                )
            )
        }
    }

    fun onPetNameChanged(name: String) {
        val stage5 = (state.stage as? Stage5) ?: return

        val newStage = stage5.copy(
            petNameFilter = name
        )
        emitState(state.copy(newStage))

        emitStateAsync(
            onError = {
                MakeAppointmentScreenState(
                    newStage.copy(lcee = LceeState.Lcee.Error(it))
                )
            }
        ) {
            state.copy(
                newStage.copy(
                    lcee = LceeState.Lcee.Content,
                    pets = repository.getPets(name = newStage.petNameFilter)
                )
            )
        }
    }

    fun goToStage6(stage3: Stage3, petEntity: PetEntity?) {
        val date = stage3.date ?: return
        val time = stage3.time ?: return

        val stage = Stage6(
            lce = Lce.Loading,
            stage3State = stage3,
            pet = petEntity,
            ownerName = "",
            email = "",
            serviceDateTime = date.atTime(time),
            isAgreeWithPersonalDataProcessing = false,
            isAppointmentInProgress = false
        )

        emitState(MakeAppointmentScreenState(stage))

        emitStateAsync(
            onError = {
                state.copy(
                    stage.copy(lce = Lce.Error(it))
                )
            }
        ) {
            val profile = repository.getProfile()
            state.copy(
                stage.copy(
                    lce = Lce.Content,
                    ownerName = profile.name,
                    email = profile.email
                )
            )
        }
    }

    fun setAgreeWithPersonalData(isAgree: Boolean) {
        val stage = (state.stage as? Stage6) ?: return

        emitState(
            state.copy(
                stage = stage.copy(
                    isAgreeWithPersonalDataProcessing = isAgree
                )
            )
        )
    }

    fun validateFields(): Boolean {
        val stage = (state.stage as? Stage6) ?: return false
        return stage.isAgreeWithPersonalDataProcessing
    }

    fun makeAppointment() {
        val stage = (state.stage as? Stage6) ?: return
        emitState(
            state.copy(
                stage.copy(isAppointmentInProgress = true)
            )
        )

        emitStateAsync(
            onError = {
                Toast.makeText(AppContextHolder.context, "Ошибка. Не получилось создать запись.", Toast.LENGTH_LONG)
                    .show()

                state.copy(
                    stage.copy(isAppointmentInProgress = false)
                )
            }
        ) {
            repository.postMakeAppointment(
                stage.pet?.id,
                stage.serviceDateTime,
                stage.stage3State.surgeonEntity.id,
                stage.stage3State.stage2State.service.toAppointmentType()
            )
            MakeAppointmentScreenState(
                SuccessScreen(
                    serviceDateTime = stage.serviceDateTime,
                    stage6State = stage
                )
            )
        }
    }

    fun onSuccessOk() {
        navHostController?.popBackStack()
    }

    fun popBack() {
        when (state.stage) {
            is Stage1 -> navHostController?.popBackStack()
            is Stage2 -> emitState(MakeAppointmentScreenState(Stage1()))
            is Stage3 -> {
                (state.stage as? Stage3)?.stage2State?.let {
                    if (it.service == Stage.Service.SURGEON) {
                        emitState(MakeAppointmentScreenState(it))
                    } else {
                        emitState(MakeAppointmentScreenState(Stage1()))
                    }
                }
            }

            is Stage4 -> {
                (state.stage as? Stage4)?.stage3State?.let {
                    emitState(MakeAppointmentScreenState(it))
                }
            }

            is Stage5 -> {
                (state.stage as? Stage5)?.stage3State?.let {
                    emitState(
                        MakeAppointmentScreenState(Stage4(it))
                    )
                }
            }

            is Stage6 -> {
                (state.stage as? Stage6)?.let {
                    if (it.pet != null) {
                        goToStage5(it.stage3State)
                    } else {
                        emitState(
                            MakeAppointmentScreenState(
                                Stage4(it.stage3State)
                            )
                        )
                    }
                }
            }

            is SuccessScreen -> {
                (state.stage as? SuccessScreen)?.stage6State?.let {
                    emitState(MakeAppointmentScreenState(it))
                }
            }
        }
    }
}