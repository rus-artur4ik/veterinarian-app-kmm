package com.rus_artur4ik.veterinarian.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rus_artur4ik.petcore.navigation.Navigator.navigateTo
import com.rus_artur4ik.petcore.navigation.Screen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.VetScreen.HomeScreen
import com.rus_artur4ik.veterinarian.VetScreen.MedCardScreen
import com.rus_artur4ik.veterinarian.VetScreen.MyPetsScreen
import com.rus_artur4ik.veterinarian.VetScreen.ProfileScreen

private val bottomBarItems = listOf(
    BottomNavItem(
        nameRes = R.string.main_screen,
        screen = HomeScreen,
        iconRes = R.drawable.home
    ),
    BottomNavItem(
        nameRes = R.string.pets_screen,
        screen = MyPetsScreen,
        iconRes = R.drawable.pets
    ),
    BottomNavItem(
        nameRes = R.string.med_card_screen,
        screen = MedCardScreen,
        iconRes = R.drawable.med_card
    ),
    BottomNavItem(
        nameRes = R.string.profile_screen,
        screen = ProfileScreen,
        iconRes = R.drawable.profile
    ),
)

@Composable
fun BottomNavBar(
    navController: NavController?,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController?.currentBackStackEntryAsState()

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
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
                        painter = painterResource(item.iconRes),
                        contentDescription = stringResource(id = item.nameRes),
                    )
                }
            )
        }
    }
}

private data class BottomNavItem(
    @StringRes val nameRes: Int,
    val screen: Screen,
    @DrawableRes val iconRes: Int,
)

@Composable
@Preview(showBackground = true)
private fun BottomNavBarPreview() {
    BottomNavBar(navController = null)
}