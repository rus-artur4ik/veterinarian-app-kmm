package com.rus_artur4ik.petcore.navigation

import com.rus_artur4ik.petcore.mvvm.CoreScreen

abstract class Screen(val id: String, val screenFactory: () -> CoreScreen<*, *>)