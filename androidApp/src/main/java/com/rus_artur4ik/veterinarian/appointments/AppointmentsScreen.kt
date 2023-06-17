package com.rus_artur4ik.veterinarian.appointments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.AppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.Header
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableScreen

class AppointmentsScreen: BaseEmptyableScreen<AppointmentsScreenState, AppointmentsViewModel>(
    AppointmentsViewModel::class.java
) {

    @Composable
    override fun NotEmpty(content: AppointmentsScreenState, viewModel: AppointmentsViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(title = stringResource(id = R.string.my_appointments))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(content.appointments) {
                    AppointmentCard(appointment = it) {
                        // TODO
                    }
                }
            }
        }
    }
}