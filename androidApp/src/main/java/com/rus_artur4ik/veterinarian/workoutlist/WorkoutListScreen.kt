package com.rus_artur4ik.veterinarian.workoutlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.Greeting
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.CoreScreen

class WorkoutListScreen : CoreScreen<WorkoutListState, WorkoutListViewModel>(
    WorkoutListViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: WorkoutListViewModel, navHostController: NavHostController?) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = Greeting().greeting(),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.default_horizontal_padding))
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensionResource(id = R.dimen.fab_padding)),
                onClick = {
                    navHostController?.navigate("new_workout")
                }
            ) {
                Image(
                    painter = rememberVectorPainter(image = Rounded.Add),
                    contentDescription = stringResource(id = R.string.add_workout)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Content()
    }
}

