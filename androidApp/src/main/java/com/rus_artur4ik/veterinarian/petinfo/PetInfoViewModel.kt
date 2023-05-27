package com.rus_artur4ik.veterinarian.petinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseViewModel
import com.rus_artur4ik.veterinarian.common.uimodel.DiagnosisUiModel
import com.rus_artur4ik.veterinarian.common.uimodel.VisitUiModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen.Companion.PET_ID_KEY
import com.rus_artur4ik.veterinarian.visitinfo.VisitInfoScreen.Companion.VISIT_ID_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PetInfoViewModel(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PetInfoScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    override fun provideInitialScreenState(): LceState<PetInfoScreenState> = LceState.loading()

    init {
        val pet = viewModelScope.async(Dispatchers.IO) {
            repository.getPet(id = savedStateHandle[PET_ID_KEY]!!)
        }
        val closestAppointment = viewModelScope.async(Dispatchers.IO) {
            repository.getAppointments(limit = 1).firstOrNull()
        }
        val visits = viewModelScope.async(Dispatchers.IO) {
            repository.getVisits(petId = savedStateHandle[PET_ID_KEY]!!)
        }

        emitStateAsync {
            PetInfoScreenState(
                pet = pet.await(),
                closestAppointment = closestAppointment.await(),
                visits = visits.await().map { VisitUiModel.from(it, listOf()) }
            )
        }
    }

    fun showAllVisits() {
        navigate(VetScreen.MedCardScreen)
    }

    fun showVisitInfo(visit: VisitUiModel) {
        navigate(VetScreen.VisitInfoScreen, mapOf(VISIT_ID_KEY to visit.id))
    }

    fun showAppointmentDetails(appointment: AppointmentEntity) {
        //TODO
    }

    fun toggleExpandDiagnosis(visitModel: VisitUiModel, diagnosisModel: DiagnosisUiModel) {
        state.doIfContent {
            val newState = it.copy(visits = it.visits.map { visit ->
                if (visit.id == visitModel.id) {
                    visit.copy(
                        diagnoses = visit.diagnoses?.map { diagnosis ->
                            if (diagnosis.diagnosis.diagnosisId == diagnosisModel.diagnosis.diagnosisId) {
                                diagnosis.copy(isExpanded = !diagnosis.isExpanded)
                            } else {
                                diagnosis
                            }
                        }
                    )
                } else {
                    visit
                }
            }
            )
            emitState(LceState.content(newState))
        }
    }

    fun popBack() {
        navHostController?.popBackStack()
    }
}