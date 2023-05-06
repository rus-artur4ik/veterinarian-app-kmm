package com.rus_artur4ik.petcore

import android.app.Application

object PetCore {

    fun initialize(application: Application) {
        AppContextHolder.application = application
    }
}