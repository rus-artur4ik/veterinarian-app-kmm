package com.rus_artur4ik.workouter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.rus_artur4ik.workouter.editweek.EditWeekScreen
import com.rus_artur4ik.workouter.newworkout.NewWorkoutScreen
import com.rus_artur4ik.workouter.workoutlist.WorkoutListScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            Content()
        }
    }
}

@Preview
@Composable
fun Content() {
    MdcTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "workout_list") {
            composable("workout_list") { WorkoutListScreen().Content(navController) }
            composable("new_workout") { NewWorkoutScreen().Content(navController) }
            composable("edit_week") { EditWeekScreen().Content(navController) }
        }
    }
}


