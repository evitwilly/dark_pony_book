package ru.freeit.evilbirth.core.prefs

interface FloatPrefs {
    fun float(key: String, default: Float) : Float
    fun saveFloat(key: String, value: Float)
}