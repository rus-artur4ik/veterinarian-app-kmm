package com.rus_artur4ik.veterinarian.appointments

import com.rus_artur4ik.veterinarian.common.AppContextHolder
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableViewModel
import com.rus_artur4ik.veterinarian.data.VetRepository

class AppointmentsViewModel: BaseEmptyableViewModel<AppointmentsScreenState>() {

    val repository = VetRepository(AppContextHolder.context)

    override fun isContentEmpty(content: AppointmentsScreenState): Boolean {
        return content.appointments.isEmpty()
    }

    init {
        emitStateAsync {
            AppointmentsScreenState(
                repository.getAppointments()
            )
        }
    }
}