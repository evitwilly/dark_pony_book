package ru.freeit.evilbirth.core.prefs

interface FloatPrefs {
    fun float(key: String, default: Float) : Float
    fun saveFloat(key: String, value: Float)
}

interface IntPrefs {
    fun int(key: String, default: Int) : Int
    fun saveInt(key: String, value: Int)
}