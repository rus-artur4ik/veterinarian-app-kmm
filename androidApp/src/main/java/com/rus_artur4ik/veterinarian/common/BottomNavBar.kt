package com.rus_artur4ik.veterinarian.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.Navigator.navigateTo

private val bottomBarItems = listOf(
    BottomNavItem(
        nameRes = R.string.main_screen,
        screen = Screen.HomeScreen,
        image = Icons.Rounded.Home
    ),
    BottomNavItem(
        nameRes = R.string.pets_screen,
        screen = Screen.MyPetsScreen,
        image = Icons.Rounded.Face
    ),
    BottomNavItem(
        nameRes = R.string.appointment_screen,
        screen = Screen.AuthScreen,
        image = Icons.Rounded.DateRange
    ),
    BottomNavItem(
        nameRes = R.string.med_card_screen,
        screen = Screen.MedCardScreen,
        image = Icons.Rounded.Add
    ),
    BottomNavItem(
        nameRes = R.string.profile_screen,
        screen = Screen.HomeScreen,
        image = Icons.Rounded.Person
    ),
)

@Composable
fun BottomNavBar(
    navController: NavController?,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController?.currentBackStackEntryAsState()

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
    ) {
        bottomBarItems.forEach { item ->
            val selected = item.screen.id == backStackEntry?.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigateTo(item.screen) },
                label = {
                    Text(
                        text = stringResource(id = item.nameRes),
                        fontSize = 10.sp
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.image,
                        contentDescription = "${stringResource(id = item.nameRes)} Icon",
                    )
                }
            )
        }
    }
}

private data class BottomNavItem(
    @StringRes val nameRes: Int,
    val screen: Screen,
    val image: ImageVector,
)

@Composable
@Preview(showBackground = true)
private fun BottomNavBarPreview() {
    BottomNavBar(navController = null)
}