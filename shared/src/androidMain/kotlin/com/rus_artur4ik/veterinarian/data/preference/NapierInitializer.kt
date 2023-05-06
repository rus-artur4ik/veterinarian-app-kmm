package com.rus_artur4ik.veterinarian.data.preference

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object NapierInitializer {

    fun initialize() {
        Napier.base(DebugAntilog())
    }
}