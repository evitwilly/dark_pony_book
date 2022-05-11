package ru.freeit.evilbirth.core.view.components

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import ru.freeit.evilbirth.core.App
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.colors.CoreColors

open class CoreTextView(ctx: Context) : AppCompatTextView(ctx) {
    private val themeManager = (context.applicationContext as App).themeManager

    open fun changePropertiesByTheme(self: CoreTextView, theme: Theme) {}

    private val listener: (Theme) -> Unit = { theme ->
        if (theme == Theme.LIGHT)
            setTextColor(CoreColors.black)
        else
            setTextColor(CoreColors.white)
        changePropertiesByTheme(this, theme)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        themeManager.addThemeListener(listener)
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        themeManager.removeThemeListener(listener)
    }
}