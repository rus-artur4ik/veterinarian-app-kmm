package com.rus_artur4ik.workouter.data

import com.rus_artur4ik.workouter.AppDatabase
import com.rus_artur4ik.workouter.AppDatabase.Companion
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}