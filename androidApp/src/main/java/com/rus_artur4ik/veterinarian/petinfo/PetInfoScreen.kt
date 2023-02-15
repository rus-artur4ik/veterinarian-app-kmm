package com.rus_artur4ik.veterinarian.petinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rus_artur4ik.petcore.mvvm.CoreScreen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.KeyValueTab
import com.rus_artur4ik.veterinarian.common.VetCard
import com.rus_artur4ik.veterinarian.common.VetScreenTemplate
import com.rus_artur4ik.veterinarian.common.formatDayFullMonth
import com.rus_artur4ik.veterinarian.common.formatDayMonthTime
import com.rus_artur4ik.veterinarian.common.formatFullDate
import com.rus_artur4ik.veterinarian.common.formatTime
import com.rus_artur4ik.veterinarian.common.localizedString
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

class PetInfoScreen: CoreScreen<PetInfoScreenState, PetInfoViewModel>(
    PetInfoViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: PetInfoViewModel, navHostController: NavHostController?) {
        VetScreenTemplate(navController = navHostController) {
            val scrollState = rememberScrollState()
            val pet = viewModel.state.value.pet
            val appointment = viewModel.state.value.closestAppointment
            val visits = viewModel.state.value.visits

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
            ) {
                SimplePetInfoCard(pet)
                Spacer(modifier = Modifier.height(22.dp))
                ExtendedPetInfoCard(pet)
                Spacer(modifier = Modifier.height(22.dp))
                ClosestAppointmentCard(appointment)
                Spacer(modifier = Modifier.height(22.dp))
                VisitsCard(visits, viewModel)
            }
        }
    }

    @Composable
    private fun SimplePetInfoCard(pet: PetEntity) {
        VetCard(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    painter = ColorPainter(Color.Gray),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )

                Column(
                    Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {

                    Text(text = pet.name)

                    Text(text = pet.breed)
                }
            }

            pet.birthday?.let {
                KeyValueTab(
                    key = stringResource(id = R.string.date_of_birth),
                    value = it.formatFullDate()
                )
            }

            KeyValueTab(
                key = stringResource(id = R.string.kind_of_pet),
                value = pet.kind
            )

            KeyValueTab(
                key = stringResource(id = R.string.sex),
                value = pet.sex.localizedString()
            )
        }
    }

    @Composable
    private fun ExtendedPetInfoCard(pet: PetEntity) {
        VetCard(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.additional_info),
                fontWeight = FontWeight.Bold
            )

            KeyValueTab(
                key = stringResource(id = R.string.color),
                value = "Красный"
            )

            KeyValueTab(
                key = stringResource(id = R.string.chip),
                value = "12345678"
            )

            KeyValueTab(
                key = stringResource(id = R.string.sterilization),
                value = "Есть"
            )
        }
    }

    @Composable
    private fun ClosestAppointmentCard(appointmentEntity: AppointmentEntity) {
        VetCard(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.closest_appointment),
                fontWeight = FontWeight.Bold
            )

            KeyValueTab(
                key = stringResource(id = R.string.service),
                value = appointmentEntity.type
            )

            KeyValueTab(
                key = stringResource(id = R.string.date),
                value = appointmentEntity.date.formatDayFullMonth()
            )

            KeyValueTab(
                key = stringResource(id = R.string.time),
                value = appointmentEntity.date.formatTime()
            )
        }
    }

    @Composable
    private fun VisitsCard(visits: List<VisitEntity>, viewModel: PetInfoViewModel) {
        VetCard(Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.visits),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Text(
                    text = stringResource(id = R.string.all),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { viewModel.showAllVisits() }
                )
            }

            Column {
                visits.take(3).forEach {
                    VisitItem(visit = it)
                }
            }
        }
    }

    @Composable
    private fun VisitItem(visit: VisitEntity) {
        Row(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray)
                .padding(vertical = 8.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(text = visit.date.formatDayMonthTime())

                KeyValueTab(
                    key = stringResource(id = R.string.diagnosis),
                    value = visit.diagnoses.reduce { acc, s -> "$acc; $s" }
                )
            }

            Image(
                painter = rememberVectorPainter(image = Icons.Rounded.ArrowForward),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        PetInfoScreen().Content()
    }
}