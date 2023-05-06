package com.rus_artur4ik.veterinarian.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.AppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.ArrowButton
import com.rus_artur4ik.veterinarian.common.composables.Header
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity

class ProfileScreen : BaseScreen<ProfileScreenState, ProfileViewModel>(
    ProfileViewModel::class.java
) {

    @Composable
    override fun Content(content: ProfileScreenState, viewModel: ProfileViewModel) {
        val scrollableState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(state = scrollableState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserCard(profile = content.profile, modifier = Modifier.padding(top = 20.dp))

            Spacer(modifier = Modifier.height(20.dp))

            content.appointments.firstOrNull()?.let { appointment ->
                Header(
                    title = stringResource(id = R.string.my_appointments),
                    subtitle = stringResource(id = R.string.all)
                ) {
                    viewModel.goToAllAppointments()
                }

                AppointmentCard(
                    appointment = appointment,
                    onMenuClick = { viewModel.openAppointmentMenu(appointment) }
                )
            }

            Header(
                title = stringResource(id = R.string.additional_info)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ArrowButton(
                text = stringResource(id = R.string.clinic_contacts),
                onClick = viewModel::goToClinicContacts
            )

            Spacer(modifier = Modifier.height(8.dp))

            ArrowButton(
                text = stringResource(id = R.string.user_agreement),
                onClick = viewModel::goToUserAgreement
            )

            Spacer(modifier = Modifier.height(8.dp))

            ArrowButton(
                text = stringResource(id = R.string.personal_data_agreement),
                onClick = viewModel::goToPersonalDataAgreement
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = viewModel::signOut,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_out),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    @Composable
    private fun UserCard(profile: ProfileEntity, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedBox(
                modifier = Modifier.size(72.dp)
            ) {
                Text(
                    text = profile.name.first().uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "${profile.name.trim()} ${profile.surname.trim()}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp)
            )

            profile.email?.let { email ->
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(
            ProfileScreenState(
                profile = ProfileEntity.generate(),
                appointments = listOf(AppointmentEntity.generate())
            ),
            ProfileViewModel()
        )
    }
}

