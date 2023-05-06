package com.rus_artur4ik.veterinarian.data.preference

import android.app.Application

actual typealias SharedPreferenceContext = Application

const val SP_NAME = "veterinarian_app"

actual fun SharedPreferenceContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).commit()
}

actual fun SharedPreferenceContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default)
}

actual fun SharedPreferenceContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).commit()
}

actual fun SharedPreferenceContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

actual fun SharedPreferenceContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).commit()
}

actual fun SharedPreferenceContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun SharedPreferenceContext.getSp() = getSharedPreferences(SP_NAME, 0)

private fun SharedPreferenceContext.getSpEditor() = getSp().edit()