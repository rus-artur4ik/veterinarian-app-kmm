package com.rus_artur4ik.veterinarian.medcard

import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository

class MedCardViewModel : BaseEmptyableViewModel<MedCardScreenState>() {

    val repository = VetRepository(AppContextHolder.context) // TODO inject it
    override fun isContentEmpty(content: MedCardScreenState): Boolean {
        return content.visits.isEmpty()
    }

    init {
        emitStateAsync {
            MedCardScreenState(
                petNameFilter = "",
                visits = repository.getVisits()
            )
        }
    }

    fun petNameFilterChanged(newName: String) {
//        emitState(state.copy(petNameFilter = newName))
    }
}