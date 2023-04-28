package com.rus_artur4ik.veterinarian.medcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.VetScreenTemplate
import com.rus_artur4ik.veterinarian.common.composables.KeyValueTab
import com.rus_artur4ik.veterinarian.common.composables.VetCard
import com.rus_artur4ik.veterinarian.common.formatDayFullMonthTime
import com.rus_artur4ik.veterinarian.domain.entity.BreedEntity
import com.rus_artur4ik.veterinarian.domain.entity.DiagnoseEntity
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.SexEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class MedCardScreen : MvvmScreen<MedCardScreenState, MedCardViewModel>(
    MedCardViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: MedCardViewModel) {
        val lazyListState = rememberLazyListState()

        VetScreenTemplate(navController = viewModel.navHostController) {
            Column(Modifier.fillMaxSize()) {

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    value = viewModel.state.petNameFilter,
                    label = {
                        Text(
                            text = "Имя питомца"
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                    onValueChange = { viewModel.petNameFilterChanged(it) }
                )

                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(
                        dimensionResource(id = R.dimen.default_horizontal_padding)
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {

                    items(count = viewModel.state.visits.size) { index ->
                        val item = viewModel.state.visits[index]
                        VisitCard(
                            item = item
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun VisitCard(item: VisitEntity, modifier: Modifier = Modifier) {
        VetCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = item.date.formatDayFullMonthTime(),
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    end = 16.dp,
                    bottom = 12.dp
                )
            )

            KeyValueTab(
                key = stringResource(id = R.string.pet),
                value = item.pet.name
            )

            KeyValueTab(
                key = stringResource(id = R.string.diagnosis),
                value = item.diagnoses.map { it.diagnoseName }.reduce { acc, s -> "$acc; $s" }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        Content(MedCardViewModel())
    }

    @Preview
    @Composable
    private fun VisitCardPreview() {
        VisitCard(
            VisitEntity(
                id = 1,
                date = LocalDateTime(
                    LocalDate.fromEpochDays(10),
                    LocalTime(1, 1, 1)
                ),
                diagnoses = listOf(
                    DiagnoseEntity(1, "Диагноз 1"),
                    DiagnoseEntity(2, "Диагноз 2")
                ),
                pet = PetEntity(
                    id = 1,
                    name = "Кеша",
                    breed = BreedEntity(1, "Ориентальная кошка"),
                    sex = SexEntity(1, "Самец"),
                    birthday = null,
                    kind = KindEntity(1, "Кот"),
                    sterilized = false
                )
            )
        )
    }
}