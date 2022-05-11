package ru.freeit.evilbirth.core.view.components

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import ru.freeit.evilbirth.core.App
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.colors.CoreColors

class CoreImageView(ctx: Context) : AppCompatImageView(ctx) {
    private val themeManager = (context.applicationContext as App).themeManager

    private val listener: (Theme) -> Unit = { theme ->
        if (theme == Theme.LIGHT)
            setColorFilter(CoreColors.black)
        else
            setColorFilter(CoreColors.white)
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