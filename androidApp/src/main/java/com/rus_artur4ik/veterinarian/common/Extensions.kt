package com.rus_artur4ik.veterinarian.common

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