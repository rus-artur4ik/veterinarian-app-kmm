package com.rus_artur4ik.veterinarian.data.preference

expect class SharedPreferenceContext

expect fun SharedPreferenceContext.putInt(key: String, value: Int)

expect fun SharedPreferenceContext.getInt(key: String, default: Int): Int

expect fun SharedPreferenceContext.putString(key: String, value: String)

expect fun SharedPreferenceContext.getString(key: String) : String?

expect fun SharedPreferenceContext.putBool(key: String, value: Boolean)

expect fun SharedPreferenceContext.getBool(key: String, default: Boolean): Boolean