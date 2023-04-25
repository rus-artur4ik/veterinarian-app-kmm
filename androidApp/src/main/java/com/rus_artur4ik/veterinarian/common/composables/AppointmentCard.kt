package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.formatFullDateTime
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType

@Composable
fun AppointmentCard(appointment: AppointmentEntity, onMenuClick: () -> Unit) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                shape = MaterialTheme.shapes.medium
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(
                        id = when (appointment.type) {
                            AppointmentType.SURGEON -> R.drawable.surgeon
                            AppointmentType.VISIT -> R.drawable.visit
                            AppointmentType.ULTRASOUND -> R.drawable.ultrasound
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.Center)
                )
            }

            Text(
                text = appointment.description,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )

            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onMenuClick() }
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 1.dp
        )

        KeyValueTab(
            key = stringResource(id = R.string.date),
            value = appointment.date.formatFullDateTime(),
            valueColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 16.dp)
        )

        KeyValueTab(
            key = stringResource(id = R.string.pet),
            value = appointment.pet.name,
            modifier = Modifier.padding(top = 10.dp)
        )

        appointment.doctorName?.let {
            KeyValueTab(
                key = stringResource(id = R.string.specialist),
                value = it,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppointmentCardPreview() {
    AppointmentCard(AppointmentEntity.generate(), {})
}