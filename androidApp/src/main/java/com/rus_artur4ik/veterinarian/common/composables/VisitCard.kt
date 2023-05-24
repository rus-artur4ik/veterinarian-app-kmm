package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rus_artur4ik.veterinarian.common.formatDayMonthYear
import com.rus_artur4ik.veterinarian.common.formatTime
import com.rus_artur4ik.veterinarian.common.getDescriptionRes
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity

@Composable
fun VisitCard(item: VisitEntity, modifier: Modifier = Modifier) {
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

@Preview
@Composable
private fun VisitCardPreview() {
    VisitCard(
        VisitEntity.generate()
    )
}