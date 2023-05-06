package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitType

@Composable
fun VisitIcon(visit: VisitEntity) {
    RoundedBox(
        modifier = Modifier.size(40.dp)
    ) {
        Image(
            painter = painterResource(
                id = when (visit.type) {
                    VisitType.FIRST -> R.drawable.initial_visit
                    VisitType.SECONDARY -> R.drawable.secondary_visit
                    VisitType.VACCINATION -> R.drawable.vaccination_visit
                }
            ), contentDescription = null, modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    VisitIcon(VisitEntity.generate())
}