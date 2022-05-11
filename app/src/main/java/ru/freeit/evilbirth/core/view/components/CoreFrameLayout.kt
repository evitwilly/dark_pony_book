package ru.freeit.evilbirth.core.view.components

import android.content.Context
import android.widget.FrameLayout
import ru.freeit.evilbirth.core.App
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.colors.CoreColors

open class CoreFrameLayout(ctx: Context) : FrameLayout(ctx) {

    private val themeManager = (context.applicationContext as App).themeManager

    open fun changePropertiesByTheme(self: CoreFrameLayout, theme: Theme) {}

    private val listener: (Theme) -> Unit = { theme ->
        if (theme == Theme.LIGHT)
            setBackgroundColor(CoreColors.white)
        else
            setBackgroundColor(CoreColors.black)
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