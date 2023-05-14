package com.rus_artur4ik.veterinarian.petinfo

import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.common.mvvm.BaseViewModel
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity

class PetInfoViewModel : BaseViewModel<PetInfoScreenState>() {

    override fun provideInitialScreenState(): LceState<PetInfoScreenState> = LceState.loading()

    fun showAllVisits() {
        //TODO
    }

    fun showAppointmentDetails(appointment: AppointmentEntity) {
        //TODO
    }

    fun toggleExpandDiagnosis(visitModel: VisitUiModel, diagnosisModel: DiagnosisUiModel) {
        state.doIfContent {
            emitState(
                LceState.content(
                    it.copy(
                        visits = it.visits.map { visit ->
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
                )
            )
        }
    }

    fun popBack() {
        navHostController?.popBackStack()
    }
}