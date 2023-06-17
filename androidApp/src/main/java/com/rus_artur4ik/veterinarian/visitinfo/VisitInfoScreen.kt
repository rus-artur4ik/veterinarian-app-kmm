package com.rus_artur4ik.veterinarian.visitinfo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.ExpandableCard
import com.rus_artur4ik.veterinarian.common.composables.HeaderWithBackButton
import com.rus_artur4ik.veterinarian.common.composables.HighlightedDescriptionCard
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.formatFullDateTime
import com.rus_artur4ik.veterinarian.common.getIconRes
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen
import com.rus_artur4ik.veterinarian.common.uimodel.VisitUiModel
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.coroutines.launch

class VisitInfoScreen : BaseScreen<VisitInfoScreenState, VisitInfoViewModel>(
    VisitInfoViewModel::class.java
) {

    private val tabs = listOf(R.string.diagnosis, R.string.research)

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content(content: VisitInfoScreenState, viewModel: VisitInfoViewModel) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { 2 }
        )

        val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxSize()) {
            HeaderWithBackButton(
                title = content.visit.date.formatFullDateTime()
            ) {
                viewModel.popBack()
            }

            Spacer(Modifier.height(12.dp))

            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, titleRes ->
                    Tab(
                        text = {
                            Text(stringResource(id = titleRes))
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState
            ) {
                when (it) {
                    0 -> DiagnosisPage(content.visit, viewModel)
                    1 -> ResearchPage(content.visit, viewModel)
                }
            }
        }
    }

    @Composable
    private fun DiagnosisPage(visit: VisitUiModel, viewModel: VisitInfoViewModel) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(20.dp))

                RoundIconCard(
                    leftTitle = visit.pet.name,
                    leftSubtitle = visit.pet.breed?.name ?: ""
                ) {
                    RoundedBox(Modifier.size(40.dp)) {
                        Image(
                            painter = painterResource(id = visit.pet.kind.getIconRes()),
                            contentDescription = null
                        )
                    }
                }
            }

            visit.weight?.let { weight ->
                item {
                    HighlightedDescriptionCard(
                        title = stringResource(id = R.string.weight),
                        subtitle = stringResource(id = R.string.weight_measurement_value, weight),
                        iconPainter = painterResource(id = R.drawable.weight_icon),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            visit.heartBeat?.let { heartBeat ->
                item {
                    HighlightedDescriptionCard(
                        title = stringResource(id = R.string.heart_beat),
                        subtitle = stringResource(id = R.string.heart_beat_measurement_value, heartBeat),
                        iconPainter = painterResource(id = R.drawable.heart_beat_icon),
                    )
                }
            }

            visit.breathBeat?.let { breathBeat ->
                item {
                    HighlightedDescriptionCard(
                        title = stringResource(id = R.string.breath_beat),
                        subtitle = stringResource(id = R.string.breath_beat_measurement_value, breathBeat),
                        iconPainter = painterResource(id = R.drawable.breath_beat_icon),
                    )
                }
            }

            items(visit.diagnoses?.size ?: 0) { index ->
                HighlightedDescriptionCard(
                    title = stringResource(id = R.string.diagnosis),
                    subtitle = visit.diagnoses?.get(index)?.diagnosis?.diagnosisName
                        ?: stringResource(id = R.string.empty_diagnosis),
                    iconPainter = painterResource(id = R.drawable.diagnosis_icon),
                    cardBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconBackgroundColor = MaterialTheme.colorScheme.secondary,
                )
            }

            visit.prescription?.let { prescription ->
                item {
                    PrescriptionCard(
                        title = stringResource(id = R.string.prescription),
                        description = prescription
                    )
                }
            }

            visit.recommendation?.let { recommendation ->
                item {
                    PrescriptionCard(
                        title = stringResource(id = R.string.recommendations),
                        description = recommendation
                    )
                }
            }

            visit.firstVisitId?.let { id ->
                item {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 20.dp),
                        onClick = { viewModel.navigateToPrimaryVisit(id) }
                    ) {
                        Text(
                            text = stringResource(id = R.string.primary_diagnosis),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun PrescriptionCard(title: String, description: String) {
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = description,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    @Composable
    private fun ResearchPage(visit: VisitUiModel, viewModel: VisitInfoViewModel) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {

                item {
                    Spacer(Modifier.height(20.dp))
                }

                items(count = visit.diagnoses?.size ?: 0) { index ->
                    val diagnosis = visit.diagnoses?.get(index) ?: return@items

                    ExpandableCard(
                        title = diagnosis.diagnosis.diagnosisType.value,
                        description = diagnosis.diagnosis.diagnosisName,
                        isExpanded = diagnosis.isExpanded,
                        onToggleExpand = { viewModel.toggleExpandDiagnosis(diagnosis) }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(VisitInfoViewModel(SavedStateHandle.createHandle(null, null)))
    }

    @Composable
    @Preview(showBackground = true)
    private fun DiagnosisPreview() {
        DiagnosisPage(
            VisitUiModel.from(VisitEntity.generate(), listOf()),
            VisitInfoViewModel(SavedStateHandle.createHandle(null, null))
        )
    }

    @Composable
    @Preview(showBackground = true)
    private fun ResearchPreview() {
        ResearchPage(
            VisitUiModel.from(VisitEntity.generate(), listOf()),
            VisitInfoViewModel(SavedStateHandle.createHandle(null, null))
        )
    }

    companion object {
        const val VISIT_ID_KEY = "visitId"
    }
}