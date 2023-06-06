package com.rus_artur4ik.veterinarian

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rus_artur4ik.petcore.mvvm.SimpleScreen
import com.rus_artur4ik.petcore.navigation.Screen
import com.rus_artur4ik.veterinarian.appointmentselector.AppointmentSelectorScreen
import com.rus_artur4ik.veterinarian.auth.AuthScreen
import com.rus_artur4ik.veterinarian.home.HomeScreen
import com.rus_artur4ik.veterinarian.medcard.MedCardScreen
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreen
import com.rus_artur4ik.veterinarian.mypetssearch.MyPetsSearchScreen
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen.Companion.PET_ID_KEY
import com.rus_artur4ik.veterinarian.profile.ProfileScreen
import com.rus_artur4ik.veterinarian.visitinfo.VisitInfoScreen
import com.rus_artur4ik.veterinarian.visitinfo.VisitInfoScreen.Companion.VISIT_ID_KEY
import com.rus_artur4ik.veterinarian.welcomescreen.WelcomeScreen

sealed class VetScreen(
    id: String,
    screenFactory: (NavBackStackEntry) -> SimpleScreen,
    arguments: List<NamedNavArgument> = listOf()
): Screen(id, arguments, screenFactory) {
    object WelcomeScreen : VetScreen("welcome", { WelcomeScreen() })
    object AuthScreen : VetScreen("auth", { AuthScreen() })
    object HomeScreen : VetScreen("main", { HomeScreen() })
    object MyPetsScreen : VetScreen("my_pets", { MyPetsScreen() })
    object MyPetsSearchScreen : VetScreen("my_pets_search", { MyPetsSearchScreen() })
    object PetInfoScreen : VetScreen(
        "pet_info",
        { PetInfoScreen() },
        listOf(navArgument(PET_ID_KEY) { type = NavType.IntType })
    )
    object MedCardScreen : VetScreen("med_card", { MedCardScreen() })
    object VisitInfoScreen: VetScreen(
        "visit_info",
        { VisitInfoScreen() },
        listOf(navArgument(VISIT_ID_KEY) { type = NavType.IntType })
    )
    object ProfileScreen : VetScreen("profile", { ProfileScreen() })
    object AppointmentSelectorScreen: VetScreen(
        "appointment_selector",
        { AppointmentSelectorScreen() }
    )
}
