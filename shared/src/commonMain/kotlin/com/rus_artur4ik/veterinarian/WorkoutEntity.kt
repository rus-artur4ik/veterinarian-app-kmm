package com.rus_artur4ik.veterinarian

data class WorkoutEntity(
    val workoutName: String,
    val weeks: List<WeekEntity>
)

data class WeekEntity(
    val id: String,
    val days: List<DayEntity>
)

data class DayEntity(
    val day: Day,
    val epochDay: Long,
    val exercises: List<ExerciseEntity>
)

data class ExerciseEntity(
    val name: String,
    val setCount: Int,
    val setWeight: Int,
    val timeToRestSeconds: Int
)

enum class Day{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}