package ru.freeit.evilbirth.core.prefs

interface IntPrefs {
    fun int(key: String, default: Int) : Int
    fun saveInt(key: String, value: Int)
}