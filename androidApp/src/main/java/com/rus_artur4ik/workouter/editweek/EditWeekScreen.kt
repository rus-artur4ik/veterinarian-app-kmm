package com.rus_artur4ik.workouter.editweek

import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rus_artur4ik.workouter.R
import com.rus_artur4ik.workouter.common.CoreScreen
import com.rus_artur4ik.workouter.common.shortNameRes
import java.time.LocalDate

class EditWeekScreen : CoreScreen<EditWeekState, EditWeekViewModel>(
    EditWeekViewModel::class.java
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(viewModel: EditWeekViewModel, navHostController: NavHostController?) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                viewModel.state.value.days.forEachIndexed() { index, day ->

                    DayChip(
                        viewModel = viewModel,
                        isDayWithTraining = day.exercises.isNotEmpty(),
                        dayOfMonth = LocalDate.ofEpochDay(day.epochDay).dayOfMonth,
                        isSelectedDay = viewModel.state.value.selectedDay == index,
                        dayName = stringResource(id = day.day.shortNameRes()),
                        dayIndex = index
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = "", onValueChange = {}, placeholder = {
                Text(text = "Количество раз")
            })

            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = "", onValueChange = {}, placeholder = {
                Text(text = "Масса")
            })

            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = "", onValueChange = {}, placeholder = {
                Text(text = "Отдых (минут)")
            })

            Button(
                modifier = Modifier
                    .padding(top = 20.dp),
                onClick = {
                    navHostController?.navigate("edit_week")
                }) {
                Text(text = stringResource(id = R.string.add_set))
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class, ExperimentalMaterial3Api::class)
    @Composable
    private fun DayChip(
        viewModel: EditWeekViewModel,
        isDayWithTraining: Boolean,
        isSelectedDay: Boolean,
        dayOfMonth: Int,
        dayName: String,
        dayIndex: Int
    ) {
        val scrollableState = rememberScrollState()

        Row(
            Modifier.scrollable(scrollableState, Horizontal)
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = dayName, fontSize = TextUnit(16f, TextUnitType.Sp))

                Card(
                    modifier = Modifier
                        .size(32.dp),
                    onClick = { viewModel.selectDay(dayIndex) }
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = dayOfMonth.toString(),
                            fontSize = TextUnit(14f, TextUnitType.Sp),
                            color = when {
                                isSelectedDay -> Color.Magenta
                                isDayWithTraining -> Color.Gray
                                else -> Color.Black
                            }
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    private enum class TextColor(val color: Color) {
        MAGENTA(Color.Magenta), GRAY(Color.Gray), WHITE(Color.White)
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content()
    }
}