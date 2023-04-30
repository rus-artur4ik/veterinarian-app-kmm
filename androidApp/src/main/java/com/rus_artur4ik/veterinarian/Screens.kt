package com.rus_artur4ik.veterinarian

import com.rus_artur4ik.petcore.mvvm.SimpleScreen
import com.rus_artur4ik.petcore.navigation.Screen
import com.rus_artur4ik.veterinarian.auth.AuthScreen
import com.rus_artur4ik.veterinarian.home.HomeScreen
import com.rus_artur4ik.veterinarian.medcard.MedCardScreen
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreen
import com.rus_artur4ik.veterinarian.petinfo.PetInfoScreen
import com.rus_artur4ik.veterinarian.profile.ProfileScreen
import com.rus_artur4ik.veterinarian.welcomescreen.WelcomeScreen

sealed class VetScreen(id: String, screenFactory: () -> SimpleScreen): Screen(id, screenFactory) {
    object WelcomeScreen : VetScreen("welcome", ::WelcomeScreen)
    object AuthScreen : VetScreen("auth", ::AuthScreen)
    object HomeScreen : VetScreen("main", ::HomeScreen)
    object MyPetsScreen : VetScreen("my_pets", ::MyPetsScreen)
    object PetInfoScreen : VetScreen("pet_info", ::PetInfoScreen)
    object MedCardScreen : VetScreen("med_card", ::MedCardScreen)
    object ProfileScreen : VetScreen("profile", ::ProfileScreen)
}
