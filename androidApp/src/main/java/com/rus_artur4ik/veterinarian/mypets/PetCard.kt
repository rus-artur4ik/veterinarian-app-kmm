package com.rus_artur4ik.veterinarian.mypets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.getIconRes
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import kotlinx.datetime.toJavaLocalDateTime

@Composable
fun PetCard(item: PetEntity, modifier: Modifier = Modifier) {
    val age = item.birthday?.toJavaLocalDateTime()?.toLocalDate()?.until(java.time.LocalDate.now())?.years
    val ageToShow = age?.let {
        if (it < 1) {
            stringResource(id = R.string.less_than_one_year)
        } else {
            pluralStringResource(id = R.plurals.years, it)
        }
    } ?: ""

    RoundIconCard(
        leftTitle = item.name,
        leftSubtitle = ageToShow,
        rightTitle = item.kind.name,
        rightSubtitle = item.breed?.name ?: "",
        modifier = modifier
    ) {
        RoundedBox(modifier = Modifier.size(40.dp)) {
            Image(
                painter = painterResource(id = item.kind.getIconRes()),
                contentDescription = null
            )
        }
    }
}