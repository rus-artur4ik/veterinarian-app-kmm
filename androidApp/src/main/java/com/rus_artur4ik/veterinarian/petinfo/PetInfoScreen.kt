package com.rus_artur4ik.veterinarian.petinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.ClosestAppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.ExpandableDiagnoseCard
import com.rus_artur4ik.veterinarian.common.composables.Header
import com.rus_artur4ik.veterinarian.common.composables.HeaderWithBackButton
import com.rus_artur4ik.veterinarian.common.composables.NoClosestAppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.composables.VisitCard
import com.rus_artur4ik.veterinarian.common.formatDayMonthYear
import com.rus_artur4ik.veterinarian.common.getIconRes
import com.rus_artur4ik.veterinarian.common.getStringRes
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen

class PetInfoScreen : BaseScreen<PetInfoScreenState, PetInfoViewModel>(
    PetInfoViewModel::class.java
) {

    @Composable
    override fun Content(content: PetInfoScreenState, viewModel: PetInfoViewModel) {
        val scrollState = rememberLazyListState()

        Column(modifier = Modifier.fillMaxSize()) {

            HeaderWithBackButton {
                viewModel.popBack()
            }

            LazyColumn(
                state = scrollState
            ) {

                item {
                    if (content.closestAppointment != null) {
                        ClosestAppointmentCard(
                            appointment = content.closestAppointment,
                            onClick = { viewModel.showAppointmentDetails(it) }
                        )
                    } else {
                        NoClosestAppointmentCard()
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))

                    RoundIconCard(
                        leftTitle = content.pet.name,
                        leftSubtitle = content.pet.breed?.name ?: ""
                    ) {
                        RoundedBox(Modifier.size(40.dp)) {
                            Image(
                                painter = painterResource(id = content.pet.kind.getIconRes()),
                                contentDescription = null
                            )
                        }
                    }
                }

                content.pet.birthday?.let { birthday ->
                    item {
                        PetInfoCard(
                            title = stringResource(id = R.string.date_of_birth),
                            subtitle = birthday.formatDayMonthYear(),
                            iconPainter = painterResource(id = R.drawable.date_of_birth_icon),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                    }
                }

                content.pet.sex?.let { sex ->
                    item {
                        PetInfoCard(
                            title = stringResource(id = R.string.sex),
                            subtitle = stringResource(sex.getStringRes()),
                            iconPainter = painterResource(id = R.drawable.sex_icon),
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                }

                item {
                    PetInfoCard(
                        title = stringResource(id = R.string.kind_of_pet),
                        subtitle = content.pet.kind.name,
                        iconPainter = painterResource(id = R.drawable.kind_icon),
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                }

                content.pet.color?.let { color ->
                    item {
                        PetInfoCard(
                            title = stringResource(id = R.string.color),
                            subtitle = color.name,
                            iconPainter = painterResource(id = R.drawable.color_icon),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                content.pet.chipNumber?.let { chipNumber ->
                    item {
                        PetInfoCard(
                            title = stringResource(id = R.string.chip),
                            subtitle = chipNumber,
                            iconPainter = painterResource(id = R.drawable.chip_icon),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                content.visits.maxByOrNull { it.date }?.let { visit ->
                    item {
                        Spacer(modifier = Modifier.height(20.dp))

                        Header(title = stringResource(id = R.string.last_visit))
                    }

                    item {
                        VisitCard(visit.toVisitEntity())
                    }
                }

                if (content.visits.flatMap { it.diagnoses ?: listOf() }.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))

                        Header(title = stringResource(id = R.string.pet_is_treating, content.pet.name))

                        Spacer(modifier = Modifier.height(16.dp))
                    }


                    content.visits.forEach { visit ->
                        visit.diagnoses?.forEach { diagnosis ->
                            item {
                                ExpandableDiagnoseCard(
                                    diagnose = diagnosis.diagnosis,
                                    isExpanded = diagnosis.isExpanded,
                                    onToggleExpand = { viewModel.toggleExpandDiagnosis(visit, diagnosis) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun PetInfoCard(
        title: String,
        subtitle: String,
        iconPainter: Painter,
        modifier: Modifier = Modifier
    ) {
        RoundIconCard(
            leftTitle = title,
            leftTitleStyle = MaterialTheme.typography.bodySmall,
            leftTitleColor = MaterialTheme.colorScheme.onSurfaceVariant,
            leftSubtitle = subtitle,
            leftSubtitleStyle = MaterialTheme.typography.titleMedium,
            leftSubtitleColor = MaterialTheme.colorScheme.secondary,
            modifier = modifier
        ) {
            RoundedBox(Modifier.size(40.dp)) {
                Image(
                    painter = iconPainter,
                    contentDescription = null
                )
            }
        }
    }

    @Composable
    private fun Preview() {
        PetInfoScreen().Content(PetInfoViewModel(SavedStateHandle.createHandle(null, null)))
    }

    companion object {
        const val PET_ID_KEY = "petId"
    }
}