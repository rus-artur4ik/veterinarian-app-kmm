package com.rus_artur4ik.veterinarian.data.preference

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SharedPreferenceContext = NSObject

actual fun SharedPreferenceContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun SharedPreferenceContext.getInt(key: String, default: Int): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

actual fun SharedPreferenceContext.putString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun SharedPreferenceContext.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}

actual fun SharedPreferenceContext.putBool(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

actual fun SharedPreferenceContext.getBool(key: String, default: Boolean): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key)
}