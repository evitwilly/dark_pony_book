package ru.freeit.evilbirth.core.prefs

import android.content.Context

class AppPrefs(ctx: Context) : IntPrefs, FloatPrefs {

    private val prefs = ctx.getSharedPreferences(app_prefs, Context.MODE_PRIVATE)
    private val edit = prefs.edit()

    override fun int(key: String, default: Int): Int = prefs.getInt(key, default)
    override fun saveInt(key: String, value: Int) { edit.putInt(key, value).apply() }

    override fun float(key: String, default: Float) : Float = prefs.getFloat(key, default)
    override fun saveFloat(key: String, value: Float) { edit.putFloat(key, value).apply() }

    companion object {
        private const val app_prefs = "application_prefs"
    }
}