package com.rus_artur4ik.veterinarian.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.Carousel
import com.rus_artur4ik.veterinarian.common.composables.ClosestAppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.Header
import com.rus_artur4ik.veterinarian.common.composables.NoClosestAppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.composables.VisitIcon
import com.rus_artur4ik.veterinarian.common.formatDayMonthYear
import com.rus_artur4ik.veterinarian.common.formatTime
import com.rus_artur4ik.veterinarian.common.getDescriptionRes
import com.rus_artur4ik.veterinarian.common.getIconRes
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.ProfileEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

class HomeScreen : BaseScreen<HomeScreenState, HomeViewModel>(
    HomeViewModel::class.java
) {

    @Composable
    override fun Content(content: HomeScreenState, viewModel: HomeViewModel) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colorScheme.surface
        )
        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        )

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            TopBar(
                profile = content.profile,
                hasUnreadNotifications = content.hasUnreadNotifications,
                viewModel = viewModel
            )

            if (content.closestAppointment != null) {
                ClosestAppointmentCard(
                    appointment = content.closestAppointment,
                    onClick = { viewModel.onAppointmentDetailsClick(it) }
                )
            } else {
                NoClosestAppointmentCard()
            }

            Spacer(modifier = Modifier.height(20.dp))

            Header(
                title = stringResource(id = R.string.my_pets),
                subtitle = stringResource(id = R.string.all),
                onSubtitleClick = {
                    viewModel.navigateToMyPets()
                }
            )

            Carousel(
                items = content.pets,
                modifier = Modifier.padding(top = 22.dp)
            ) {
                PetCard(
                    pet = it,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { viewModel.navigateToPetInfo(it) }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (content.visits.isNotEmpty()) {
                Header(
                    title = stringResource(id = R.string.last_visits),
                    subtitle = stringResource(id = R.string.all),
                    onSubtitleClick = {
                        viewModel.navigateToMedCard()
                    }
                )
            }

            content.visits.forEach {
                VisitCard(visit = it, viewModel)
            }
        }
    }

    @Composable
    private fun PetCard(pet: PetEntity, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier.size(130.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundedBox(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = pet.kind.getIconRes()),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
                )

                Text(
                    text = pet.kind.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
                )
            }

        }
    }

    @Composable
    private fun VisitCard(visit: VisitEntity, viewModel: HomeViewModel) {
        RoundIconCard(
            leftTitle = visit.pet.name,
            leftSubtitle = stringResource(id = visit.type.getDescriptionRes()),
            rightTitle = visit.date.formatDayMonthYear(),
            rightSubtitle = visit.date.formatTime(),
            modifier = Modifier.clickable {
                viewModel.navigateToVisitInfo(visit)
            }
        ) {
            VisitIcon(visit)
        }
    }

    @Composable
    private fun TopBar(profile: ProfileEntity, hasUnreadNotifications: Boolean, viewModel: HomeViewModel) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 28.dp, top = 16.dp, bottom = 16.dp)
        ) {
            RoundedBox(modifier = Modifier.size(40.dp)) {
                Text(
                    text = profile.name.trim().first().uppercase(),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Column(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "${profile.name.trim()} ${profile.surname.trim()}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                profile.email?.let { email ->
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }


            Image(
                painter = painterResource(
                    id = if (hasUnreadNotifications) {
                        R.drawable.ring_with_dot
                    } else {
                        R.drawable.ring
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically)
                    .clickable {
                        viewModel.navigateToNotifications()
                    }
            )
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(HomeViewModel())
    }

    @Composable
    @Preview(showBackground = true)
    private fun PetCardPreview() {
        PetCard(PetEntity.generate())
    }

    @Composable
    @Preview(showBackground = true)
    private fun VisitCardPreview() {
        VisitCard(VisitEntity.generate(), HomeViewModel())
    }

    @Composable
    @Preview(showBackground = true)
    private fun TopBarPreview() {
        TopBar(ProfileEntity.generate(), true, HomeViewModel())
    }
}