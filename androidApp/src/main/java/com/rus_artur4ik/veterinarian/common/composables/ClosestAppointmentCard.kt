package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.formatDayFullMonthTime
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity

@Composable
fun ClosestAppointmentCard(appointment: AppointmentEntity, onClick: (AppointmentEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.paws_down),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            Column {
                Text(
                    text = stringResource(id = R.string.closest_appointment),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                )

                Text(
                    text = appointment.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp)
                )

                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar_16),
                        contentDescription = null
                    )

                    Text(
                        text = appointment.date.formatDayFullMonthTime(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                OutlinedButton(
                    onClick = { onClick(appointment) },
                    modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.detailed),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun NoClosestAppointmentCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.paws_down),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            Column {
                Text(
                    text = stringResource(id = R.string.no_closest_appointment),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                )

                Text(
                    text = stringResource(id = R.string.no_closest_appointment_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 20.dp)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun ClosestAppointmentCardPreview() {
    ClosestAppointmentCard(AppointmentEntity.generate()) { }
}

@Composable
@Preview(showBackground = true)
private fun NoClosestAppointmentCardPreview() {
    NoClosestAppointmentCard()
}