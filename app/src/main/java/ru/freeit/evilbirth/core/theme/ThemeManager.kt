package ru.freeit.evilbirth.core.theme

import ru.freeit.evilbirth.core.prefs.IntPrefs

class ThemeManager(private val prefs: IntPrefs) {

    private var currentTheme = Theme.LIGHT
    private val listeners = mutableSetOf<(theme: Theme) -> Unit>()

    init {
        currentTheme = Theme.values()[prefs.int(themeKey, 0)]
    }

    fun theme() = currentTheme

    fun addThemeListener(listener: (Theme) -> Unit) {
        listeners.add(listener)
        listener.invoke(currentTheme)
    }
    fun removeThemeListener(listener: (Theme) -> Unit) { listeners.remove(listener) }

    fun light() { changeTheme(Theme.LIGHT) }
    fun dark() { changeTheme(Theme.DARK) }

    private fun changeTheme(newTheme: Theme) {
        if (currentTheme == newTheme) return
        prefs.saveInt(themeKey, newTheme.ordinal)
        currentTheme = newTheme
        listeners.forEach { it.invoke(newTheme) }
    }

    fun toggleTheme() {
        val newTheme = if (currentTheme == Theme.LIGHT) Theme.DARK else Theme.LIGHT
        prefs.saveInt(themeKey, newTheme.ordinal)
        currentTheme = newTheme
        listeners.forEach { it.invoke(newTheme) }
    }

    companion object {
        private const val themeKey = "theme_manager_theme_key"
    }

}