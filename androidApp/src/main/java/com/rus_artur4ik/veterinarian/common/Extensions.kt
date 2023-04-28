package com.rus_artur4ik.veterinarian.common

import androidx.annotation.DrawableRes
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
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