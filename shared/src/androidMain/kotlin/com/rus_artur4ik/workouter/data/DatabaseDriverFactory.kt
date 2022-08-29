package com.rus_artur4ik.workouter.data

import android.content.Context
import com.rus_artur4ik.workouter.AppDatabase
import com.rus_artur4ik.workouter.AppDatabase.Companion
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
    }
}