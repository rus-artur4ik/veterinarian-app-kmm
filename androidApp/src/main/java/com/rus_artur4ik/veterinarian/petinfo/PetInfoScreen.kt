package com.rus_artur4ik.veterinarian.petinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.ClosestAppointmentCard
import com.rus_artur4ik.veterinarian.common.composables.ExpandableCard
import com.rus_artur4ik.veterinarian.common.composables.Header
import com.rus_artur4ik.veterinarian.common.composables.HeaderWithBackButton
import com.rus_artur4ik.veterinarian.common.composables.HighlightedDescriptionCard
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
                        HighlightedDescriptionCard(
                            title = stringResource(id = R.string.date_of_birth),
                            subtitle = birthday.formatDayMonthYear(),
                            iconPainter = painterResource(id = R.drawable.date_of_birth_icon),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

                content.pet.sex?.let { sex ->
                    item {
                        HighlightedDescriptionCard(
                            title = stringResource(id = R.string.sex),
                            subtitle = stringResource(sex.getStringRes()),
                            iconPainter = painterResource(id = R.drawable.sex_icon)
                        )
                    }
                }

                item {
                    HighlightedDescriptionCard(
                        title = stringResource(id = R.string.kind_of_pet),
                        subtitle = content.pet.kind.name,
                        iconPainter = painterResource(id = R.drawable.kind_icon)
                    )
                }

                content.pet.color?.let { color ->
                    item {
                        HighlightedDescriptionCard(
                            title = stringResource(id = R.string.color),
                            subtitle = color.name,
                            iconPainter = painterResource(id = R.drawable.color_icon),
                        )
                    }
                }

                content.pet.chipNumber?.let { chipNumber ->
                    item {
                        HighlightedDescriptionCard(
                            title = stringResource(id = R.string.chip),
                            subtitle = chipNumber,
                            iconPainter = painterResource(id = R.drawable.chip_icon),
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
                                ExpandableCard(
                                    title = diagnosis.diagnosis.diagnosisType.value,
                                    description = diagnosis.diagnosis.diagnosisName,
                                    isExpanded = diagnosis.isExpanded,
                                    onToggleExpand = { viewModel.toggleExpandDiagnosis(visit, diagnosis) }
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
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