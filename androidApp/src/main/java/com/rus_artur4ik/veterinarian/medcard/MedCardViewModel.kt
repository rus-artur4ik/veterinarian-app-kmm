package com.rus_artur4ik.veterinarian.medcard

import com.rus_artur4ik.veterinarian.VetScreen.MyPetsSearchScreen
import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

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

    fun openVisitInfo(visit: VisitEntity) {
        // TODO
    }

    fun navigateToSearchMode() {
        navigate(MyPetsSearchScreen)
    }
}