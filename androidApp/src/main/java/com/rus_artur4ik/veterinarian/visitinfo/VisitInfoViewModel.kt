package com.rus_artur4ik.veterinarian.visitinfo

import androidx.lifecycle.SavedStateHandle
import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.VetScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseViewModel
import com.rus_artur4ik.veterinarian.common.uimodel.DiagnosisUiModel
import com.rus_artur4ik.veterinarian.common.uimodel.VisitUiModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.visitinfo.VisitInfoScreen.Companion.VISIT_ID_KEY

class VisitInfoViewModel(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<VisitInfoScreenState>() {

    private val repository = VetRepository(AppContextHolder.context)

    init {
        emitStateAsync {
            VisitInfoScreenState(
                visit = VisitUiModel.from(
                    visitEntity = repository.getVisit(savedStateHandle[VISIT_ID_KEY]!!),
                    listOf()
                )
            )
        }
    }

    override fun provideInitialScreenState(): LceState<VisitInfoScreenState> = LceState.loading()

    fun toggleExpandDiagnosis(diagnosisModel: DiagnosisUiModel) {
        state.doIfContent {
            val newState = it.copy(
                visit = it.visit.copy(
                    diagnoses = it.visit.diagnoses?.map { diagnosis ->
                        if (diagnosis.diagnosis.diagnosisId == diagnosisModel.diagnosis.diagnosisId) {
                            diagnosis.copy(isExpanded = !diagnosis.isExpanded)
                        } else {
                            diagnosis
                        }
                    }
                )
            )
            emitState(LceState.content(newState))
        }
    }

fun navigateToPrimaryVisit(visitId: Int) {
    navigate(VetScreen.VisitInfoScreen, mapOf(VISIT_ID_KEY to visitId))
}

fun popBack() {
    navHostController?.popBackStack()
}
}