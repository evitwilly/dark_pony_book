package ru.freeit.evilbirth.core.view.components

import android.content.Context
import android.widget.LinearLayout
import ru.freeit.evilbirth.core.App
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.colors.CoreColors

class CoreLinearLayout(ctx: Context) : LinearLayout(ctx) {

    private val themeManager = (context.applicationContext as App).themeManager

    private val listener: (Theme) -> Unit = { theme ->
        if (theme == Theme.LIGHT)
            setBackgroundColor(CoreColors.white)
        else
            setBackgroundColor(CoreColors.black)
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