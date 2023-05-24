package com.rus_artur4ik.veterinarian.visitinfo

import com.rus_artur4ik.petcore.mvvm.lce.LceState
import com.rus_artur4ik.veterinarian.common.mvvm.BaseViewModel

class VisitInfoViewModel : BaseViewModel<VisitInfoScreenState>() {

    override fun provideInitialScreenState(): LceState<VisitInfoScreenState> = LceState.loading()

    fun popBack() {
        navHostController?.popBackStack()
    }
}