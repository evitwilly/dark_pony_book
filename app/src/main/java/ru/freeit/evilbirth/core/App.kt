package ru.freeit.evilbirth.core

import android.app.Application
import ru.freeit.evilbirth.core.prefs.AppPrefs
import ru.freeit.evilbirth.core.theme.ThemeManager

class App : Application() {
    val prefs by lazy { AppPrefs(this) }
    val themeManager by lazy { ThemeManager(prefs) }
}