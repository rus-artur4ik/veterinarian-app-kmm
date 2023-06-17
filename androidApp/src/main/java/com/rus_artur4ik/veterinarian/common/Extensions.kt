package com.rus_artur4ik.veterinarian.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
import com.rus_artur4ik.veterinarian.domain.entity.SexEntity
import com.rus_artur4ik.veterinarian.domain.entity.VisitType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatDayFullMonthTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDateTime.formatDayMonthYear(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.toJavaLocalDateTime().format(dateTimeFormatter)
}

fun LocalDate.formatDayMonthYear(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.toJavaLocalDate().format(dateFormatter)
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

fun LocalTime.formatTime(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.toJavaLocalTime().format(dateTimeFormatter)
}

//----------------------------------------------------------------------

@DrawableRes
fun KindEntity.getIconRes(): Int {
    return when (id) {
        52 -> R.drawable.pet1
        53, 188, 189, 190 -> R.drawable.pet2
        191 -> R.drawable.pet3
        38 -> R.drawable.pet4
        185 -> R.drawable.pet5
        54, 67, 187, 192 -> R.drawable.pet6
        181, 183, 186 -> R.drawable.pet7
        70, 179 -> R.drawable.pet8
        68 -> R.drawable.pet9
        else -> R.drawable.pet10
    }
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
        7 -> R.string.female
        8 -> R.string.male
        else -> R.string.other_sex
    }
}