package com.rus_artur4ik.veterinarian.appointmentselector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rus_artur4ik.petcore.mvvm.SimpleScreen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.Header

class AppointmentSelectorScreen : SimpleScreen() {

    @Composable
    override fun Content(navHostController: NavHostController?) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(title = stringResource(id = R.string.appointment_to_clinic))

            Spacer(Modifier.height(24.dp))

            InfoCard(
                title = stringResource(id = R.string.make_an_appointment),
                infoText = stringResource(id = R.string.make_appointment_info)
            ) {
                //TODO
            }

            Spacer(Modifier.height(8.dp))

            InfoCard(
                title = stringResource(id = R.string.notify_about_visit),
                infoText = stringResource(id = R.string.notify_about_visit_info)
            ) {
                //TODO
            }
        }
    }

    @Composable
    private fun InfoCard(
        title: String,
        infoText: String,
        onClick: () -> Unit
    ) {
        val isInfoExpanded = remember { mutableStateOf(false) }
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable { onClick() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.info_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(24.dp)
                            .clickable {
                                isInfoExpanded.value = true
                            }
                    )
                    DropdownMenu(
                        expanded = isInfoExpanded.value,
                        onDismissRequest = { isInfoExpanded.value = false },
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
                            )
                    ) {
                        Text(
                            text = infoText,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .widthIn(max = 240.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(null)
    }
}