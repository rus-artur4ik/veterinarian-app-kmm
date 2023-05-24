package com.rus_artur4ik.veterinarian.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
import com.rus_artur4ik.veterinarian.domain.entity.SexEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitType
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatDayFullMonthTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatDayMonthYear(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatFullDateTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")
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

//----------------------------------------------------------------------

@DrawableRes
fun KindEntity.getIconRes(): Int {
    return R.drawable.pet1  // TODO
}

@StringRes
fun VisitType.getDescriptionRes(): Int {
    return when (this) {
        VisitType.FIRST -> R.string.initial_visit
        VisitType.SECONDARY -> R.string.secondary_visit
        VisitType.VACCINATION -> R.string.vaccination_visit
    }
}

@StringRes
fun SexEntity.getStringRes(): Int {
    return when (id) {
        0 -> R.string.male
        else -> R.string.female //TODO
    }
}