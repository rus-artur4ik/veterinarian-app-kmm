package com.rus_artur4ik.veterinarian.mypets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen
import com.rus_artur4ik.veterinarian.mypets.WorkoutItem.DayItem
import com.rus_artur4ik.veterinarian.mypets.WorkoutItem.WeekItem

class MyPetsScreen : CoreScreen<NewWorkoutState, MyPetsViewModel>(
    MyPetsViewModel::class.java
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(viewModel: MyPetsViewModel, navHostController: NavHostController?) {
        val lazyListState = rememberLazyListState()

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_horizontal_padding)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    TextField(
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                        value = viewModel.state.value.workoutName,
                        onValueChange = { viewModel.onWorkoutNameChanged(it) }
                    )
                }

                items(count = viewModel.state.value.items.size) { index ->
                    when (val item = viewModel.state.value.items[index]) {
                        is WeekItem -> WeekItem(item, showDeleteButton = item.showDelete)
                        is DayItem -> DayItem(item)
                    }
                }

                item {
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        onClick = {
                            navHostController?.navigate("edit_week")
                        }) {
                        Text(text = stringResource(id = R.string.add_week))
                    }
                }

                item {
                    Button(
                        modifier = Modifier
                            .padding(bottom = 12.dp, top = 40.dp),
                        onClick = {
                            navHostController?.popBackStack()
                        }) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }

            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    private fun WeekItem(item: WeekItem, showDeleteButton: Boolean) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            val (text, edit, delete) = createRefs()

            Text(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = stringResource(R.string.week, item.ordinal),
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(24f, TextUnitType.Sp)
            )

            Image(
                modifier = Modifier
                    .constrainAs(edit) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(text.end, 12.dp)
                    },
                painter = rememberVectorPainter(image = Outlined.Edit),
                contentDescription = null
            )

            if (showDeleteButton) {
                Image(
                    modifier = Modifier
                        .constrainAs(delete) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(edit.end, 4.dp)
                        },
                    painter = rememberVectorPainter(image = Outlined.Delete),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    private fun DayItem(item: DayItem) {
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = stringResource(R.string.day, item.dayName, item.maxWeight),
            fontSize = TextUnit(18f, TextUnitType.Sp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        Content()
    }
}