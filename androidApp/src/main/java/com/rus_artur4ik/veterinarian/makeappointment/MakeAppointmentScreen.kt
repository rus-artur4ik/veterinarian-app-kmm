package com.rus_artur4ik.veterinarian.makeappointment

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.petcore.mvvm.lce.Lce
import com.rus_artur4ik.petcore.mvvm.lce.LceeState
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.ArrowButton
import com.rus_artur4ik.veterinarian.common.composables.ErrorIndicator
import com.rus_artur4ik.veterinarian.common.composables.HeaderWithBackButton
import com.rus_artur4ik.veterinarian.common.composables.KeyValueTab
import com.rus_artur4ik.veterinarian.common.composables.LoadingIndicator
import com.rus_artur4ik.veterinarian.common.formatDayFullMonth
import com.rus_artur4ik.veterinarian.common.formatDayMonthYear
import com.rus_artur4ik.veterinarian.common.formatFullDateTime
import com.rus_artur4ik.veterinarian.common.formatTime
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage1
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage2
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage3
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage4
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage5
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.Stage6
import com.rus_artur4ik.veterinarian.makeappointment.MakeAppointmentScreenState.Stage.SuccessScreen
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class MakeAppointmentScreen : MvvmScreen<MakeAppointmentScreenState, MakeAppointmentViewModel>(
    MakeAppointmentViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: MakeAppointmentViewModel) {
        BackHandler {
            viewModel.popBack()
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val progress = animateFloatAsState(
                targetValue = viewModel.state.stage.progress,
                label = "Progress Animation"
            )
            HeaderWithBackButton(title = stringResource(id = R.string.make_appointment)) {
                viewModel.popBack()
            }

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = progress.value,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            AnimatedContent(
                targetState = viewModel.state.stage,
                label = "animatedContent",
                transitionSpec = {
                    if (targetState.progress == initialState.progress) {
                        fadeIn() togetherWith fadeOut()
                    } else if (targetState.progress > initialState.progress) {
                        slideIntoContainer(Start) togetherWith
                            slideOutOfContainer(Start) + fadeOut()
                    } else {
                        slideIntoContainer(End) togetherWith
                            slideOutOfContainer(End) + fadeOut()
                    }
                },
                contentKey = { targetState -> targetState.progress }
            ) { stage ->
                when (stage) {
                    is Stage1 -> Stage1Screen(stage, viewModel)
                    is Stage2 -> Stage2Screen(stage, viewModel)
                    is Stage3 -> Stage3Screen(stage, viewModel)
                    is Stage4 -> Stage4Screen(viewModel)
                    is Stage5 -> Stage5Screen(stage, viewModel)
                    is Stage6 -> Stage6Screen(stage, viewModel)
                    is SuccessScreen -> SuccessScreen(stage, viewModel)
                }
            }
        }
    }

    @Composable
    private fun Stage1Screen(state: Stage1, viewModel: MakeAppointmentViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.select_service),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(20.dp))

            state.items.forEach {
                ArrowButton(
                    text = stringResource(id = it.nameRes),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    viewModel.onStage1ServiceClick(it)
                }
            }
        }
    }

    @Composable
    private fun Stage2Screen(state: Stage2, viewModel: MakeAppointmentViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.select_service),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                value = state.name,
                label = {
                    Text(
                        text = stringResource(id = R.string.search_service)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                onValueChange = {
                    viewModel.changeServiceSearchQuery(it)
                }
            )

            LazyColumn(
                content = {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    when (state.lce) {
                        is Lce.Content -> {
                            items(items = state.items) {
                                ArrowButton(
                                    text = it.name,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    viewModel.onStage2ServiceClick(it)
                                }
                            }
                        }

                        is Lce.Error -> {
                            item {
                                ErrorIndicator(t = state.lce.throwable)
                            }
                        }

                        is Lce.Loading -> {
                            item {
                                LoadingIndicator()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Stage3Screen(state: Stage3, viewModel: MakeAppointmentViewModel) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (state.date != null && state.time != null) {

                    OutlinedButton(
                        onClick = viewModel::goToStage4,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.continue_button),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            },
            content = { paddings ->
                Column(modifier = Modifier.padding(paddings)) {

                    Text(
                        text = stringResource(id = R.string.select_date_and_time),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    val datePickerState = rememberDatePickerState()

                    if (state.isCalendarExpanded) {
                        DatePickerDialog(
                            onDismissRequest = {
                                val date = datePickerState.selectedDateMillis?.let { millis ->
                                    LocalDate.fromEpochDays((millis / 86400000).toInt())
                                }
                                viewModel.expandCalendar(false, date)
                            },
                            confirmButton = {
                                Text(
                                    text = stringResource(id = R.string.ready),
                                    modifier = Modifier
                                        .padding(end = 16.dp, bottom = 16.dp)
                                        .clickable {
                                            val date = datePickerState.selectedDateMillis?.let { millis ->
                                                LocalDate.fromEpochDays((millis / 86400000).toInt())
                                            }
                                            viewModel.expandCalendar(false, date)
                                        }
                                )
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .clickable { viewModel.expandCalendar(true, state.date) },
                        value = state.date?.formatDayMonthYear() ?: "",
                        label = {
                            Text(
                                text = stringResource(id = R.string.date)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar_28),
                                contentDescription = null
                            )
                        },
                        onValueChange = {},
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                            disabledTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        value = state.time?.formatTime() ?: "",
                        label = {
                            Text(
                                text = stringResource(id = R.string.time)
                            )
                        },
                        onValueChange = {},
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                            disabledTextColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    if (state.date != null) {
                        when (state.lce) {
                            is Lce.Content -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(4),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                    content = {
                                        items(items = state.availableTimes.freeTimes) {
                                            TimeChip(
                                                time = it.time,
                                                isSelected = state.time == it.time,
                                                viewModel = viewModel
                                            )
                                        }
                                    }
                                )
                            }

                            is Lce.Error -> {
                                ErrorIndicator(t = state.lce.throwable)
                            }

                            is Lce.Loading -> {
                                LoadingIndicator()
                            }
                        }
                    }
                }
            }
        )
    }

    @Composable
    private fun TimeChip(time: LocalTime, isSelected: Boolean, viewModel: MakeAppointmentViewModel) {
        val backgroundColor = animateColorAsState(
            targetValue = if (isSelected) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            },
            label = "Chip background",
            animationSpec = tween(durationMillis = 500)
        )
        val textColor = animateColorAsState(
            targetValue = if (isSelected) {
                MaterialTheme.colorScheme.onSecondary
            } else {
                MaterialTheme.colorScheme.onSecondaryContainer
            },
            label = "Chip text color",
            animationSpec = tween(durationMillis = 500)
        )

        Card(
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor.value
            ),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    viewModel.setTime(time)
                }
        ) {
            Text(
                text = time.formatTime(),
                style = MaterialTheme.typography.labelLarge,
                color = textColor.value,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            )
        }
    }

    @Composable
    private fun Stage4Screen(viewModel: MakeAppointmentViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.select_pet),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ArrowButton(
                text = stringResource(id = R.string.new_pet),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                viewModel.selectNewPet()
            }

            ArrowButton(
                text = stringResource(id = R.string.existing_pet),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                viewModel.selectExistingPet()
            }
        }
    }

    @Composable
    private fun Stage5Screen(stage: Stage5, viewModel: MakeAppointmentViewModel) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.select_pet),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = stage.petNameFilter,
                label = {
                    Text(
                        text = stringResource(id = R.string.search_pet)
                    )
                },
                onValueChange = { viewModel.onPetNameChanged(it) }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    Spacer(Modifier.height(16.dp))
                }

                when (stage.lcee) {
                    LceeState.Lcee.Content -> {
                        items(stage.pets) {
                            ArrowButton(
                                text = it.name,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                viewModel.goToStage6(stage.stage3State, it)
                            }
                        }
                    }

                    LceeState.Lcee.Empty -> {
                        item {
                            Text(text = stringResource(id = R.string.no_pets_found))
                        }
                    }

                    is LceeState.Lcee.Error -> {
                        item {
                            ErrorIndicator(t = stage.lcee.throwable)
                        }
                    }

                    LceeState.Lcee.Loading -> {
                        item {
                            LoadingIndicator()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Stage6Screen(state: Stage6, viewModel: MakeAppointmentViewModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.finish_appointment),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {

                Spacer(Modifier.height(16.dp))

                KeyValueTab(
                    key = stringResource(id = R.string.pet),
                    value = state.pet?.name ?: stringResource(id = R.string.new_pet),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                )

                KeyValueTab(
                    key = stringResource(id = R.string.your_name),
                    value = state.ownerName,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                )

                KeyValueTab(
                    key = stringResource(id = R.string.service),
                    value = state.stage3State.surgeonEntity.name,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                )

                KeyValueTab(
                    key = stringResource(id = R.string.date_of_appointment),
                    value = state.serviceDateTime.formatFullDateTime(),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Checkbox(
                    checked = state.isAgreeWithPersonalDataProcessing,
                    onCheckedChange = { viewModel.setAgreeWithPersonalData(it) }
                )

                Text(
                    text = stringResource(id = R.string.personal_data_agreement_checkbox),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(CenterVertically)
                        .clickable {
                            viewModel.setAgreeWithPersonalData(
                                !state.isAgreeWithPersonalDataProcessing
                            )
                        }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            FilledIconButton(
                onClick = { viewModel.makeAppointment() },
                enabled = viewModel.validateFields(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (state.isAppointmentInProgress) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        text = stringResource(id = R.string.make_an_appointment_short),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SuccessScreen(stage: SuccessScreen, viewModel: MakeAppointmentViewModel) {
        AlertDialog(
            onDismissRequest = { viewModel.popBack() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    shape = MaterialTheme.shapes.extraLarge
                )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.you_are_appointed),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp)
                )

                Text(
                    text = stringResource(
                        id = R.string.wait_for_you,
                        stage.serviceDateTime.formatDayFullMonth(),
                        stage.serviceDateTime.formatTime()
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)
                )

                Text(
                    text = stringResource(id = R.string.ok),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(bottom = 34.dp, top = 34.dp, end = 36.dp)
                        .align(Alignment.End)
                        .clickable { viewModel.onSuccessOk() }
                )
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Stage1Preview() {
        Stage1Screen(
            state = Stage1(),
            viewModel = MakeAppointmentViewModel()
        )
    }
}