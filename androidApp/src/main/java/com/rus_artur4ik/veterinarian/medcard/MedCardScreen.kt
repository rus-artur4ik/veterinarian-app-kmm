package com.rus_artur4ik.veterinarian.medcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.VisitIcon
import com.rus_artur4ik.veterinarian.common.formatDayMonthYear
import com.rus_artur4ik.veterinarian.common.formatTime
import com.rus_artur4ik.veterinarian.common.getDescriptionRes
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableScreen
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

class MedCardScreen : BaseEmptyableScreen<MedCardScreenState, MedCardViewModel>(
    MedCardViewModel::class.java
) {

    @Composable
    override fun Wrapper(viewModel: () -> MedCardViewModel, content: @Composable () -> Unit) {
        super.Wrapper(viewModel) {
            ShowAllModeWrapper(viewModel = viewModel()) {
                content()
            }
        }
    }

    @Composable
    override fun NotEmpty(content: MedCardScreenState, viewModel: MedCardViewModel) {
        List(content, rememberLazyListState(), viewModel)
    }

    @Composable
    override fun Empty(content: MedCardScreenState, viewModel: MedCardViewModel) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.you_have_no_visits),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun List(
        content: MedCardScreenState,
        listState: LazyListState,
        viewModel: MedCardViewModel,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {

            items(count = content.visits.size) { index ->
                val item = content.visits[index]
                VisitCard(
                    item = item,
                    modifier = Modifier
                        .clickable { viewModel.openVisitInfo(item) }
                )
            }
        }
    }

    @Composable
    private fun ShowAllModeWrapper(
        viewModel: MedCardViewModel,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.medcard_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 16.dp)
                        .align(Alignment.CenterStart)
                )

                Image(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 28.dp)
                        .size(24.dp)
                        .clickable {
                            viewModel.navigateToSearchMode()
                        }
                )
            }

            content()
        }
    }

    @Composable
    private fun VisitCard(item: VisitEntity, modifier: Modifier = Modifier) {
        RoundIconCard(
            leftTitle = item.pet.name,
            leftSubtitle = stringResource(id = item.type.getDescriptionRes()),
            rightTitle = item.date.formatDayMonthYear(),
            rightSubtitle = item.date.formatTime(),
            modifier = modifier
        ) {
            VisitIcon(visit = item)
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
            VisitEntity.generate()
        )
    }
}