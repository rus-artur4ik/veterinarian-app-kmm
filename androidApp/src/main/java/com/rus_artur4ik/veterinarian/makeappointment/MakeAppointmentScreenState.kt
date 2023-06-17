package com.rus_artur4ik.veterinarian.makeappointment

import androidx.annotation.StringRes
import com.rus_artur4ik.petcore.mvvm.MvvmState
import com.rus_artur4ik.petcore.mvvm.lce.Lce
import com.rus_artur4ik.petcore.mvvm.lce.LceeState
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.domain.entity.AppointmentType
import com.rus_artur4ik.veterinarian.domain.entity.AvailableTimesEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.SurgeonEntity
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Service.SURGEON
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Service.ULTRASOUND
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class MakeAppointmentScreenState(
    val stage: Stage
): MvvmState {
    sealed class Stage(val progress: Float) {

        enum class Service(@StringRes val nameRes: Int) {
            ULTRASOUND(R.string.ultrasound),
            SURGEON(R.string.surgeon);

            fun toAppointmentType(): AppointmentType {
                return when (this) {
                    ULTRASOUND -> AppointmentType.ULTRASOUND
                    SURGEON -> AppointmentType.SURGEON
                }
            }
        }

        data class Stage1(
            val items: List<Service> = listOf(ULTRASOUND, SURGEON)
        ): Stage(0.14f)

        data class Stage2(
            val lce: Lce,
            val service: Service,
            val items: List<SurgeonEntity>,
            val name: String
        ): Stage(0.28f)

        data class Stage3(
            val lce: Lce,
            val stage2State: Stage2,
            val surgeonEntity: SurgeonEntity,
            val date: LocalDate?,
            val time: LocalTime?,
            val availableTimes: AvailableTimesEntity,
            val isCalendarExpanded: Boolean
        ): Stage(0.43f)

        data class Stage4(
            val stage3State: Stage3,
        ): Stage(0.57f)

        data class Stage5(
            val stage3State: Stage3,
            val lcee: LceeState.Lcee,
            val petNameFilter: String,
            val pets: List<PetEntity>
        ): Stage(0.71f)

        data class Stage6(
            val lce: Lce,
            val stage3State: Stage3,
            val pet: PetEntity?,
            val ownerName: String,
            val email: String,
            val serviceDateTime: LocalDateTime,
            val isAgreeWithPersonalDataProcessing: Boolean,
            val isAppointmentInProgress: Boolean
        ): Stage(0.86f)

        data class SuccessScreen(
            val serviceDateTime: LocalDateTime,
            val stage6State: Stage6
        ): Stage(1f)
    }
}