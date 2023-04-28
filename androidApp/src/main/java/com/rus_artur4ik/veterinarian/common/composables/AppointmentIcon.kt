package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType

@Composable
fun AppointmentIcon(appointment: AppointmentEntity) {
    RoundedBox(
        modifier = Modifier.size(40.dp)
    ) {
        Image(
            painter = painterResource(
                id = when (appointment.type) {
                    AppointmentType.SURGEON -> R.drawable.surgeon
                    AppointmentType.VISIT -> R.drawable.visit
                    AppointmentType.ULTRASOUND -> R.drawable.ultrasound
                }
            ), contentDescription = null, modifier = Modifier.size(16.dp)
        )
    }
}