package com.rus_artur4ik.veterinarian.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.Sex
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatDayMonthTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatFullDate(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatDayFullMonth(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

@Composable
fun Sex.localizedString(): String {
    return when(this) {
        Sex.MALE -> stringResource(id = R.string.male)
        Sex.FEMALE -> stringResource(id = R.string.female)
    }
}