package ru.freeit.evilbirth.chapter

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.*
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.layout.frameLayoutParams
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.typeface.opensans_medium
import kotlin.math.roundToInt

class SettingsFragment : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeTitle(getString(R.string.settings))

        val scroll = ScrollView(ctx)
        addViewToRoot(scroll)

        val linear = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            padding(ctx.dp(16))
        }
        scroll.addView(linear)

        linear.addView(TextView(ctx).apply {
            typeface = opensans_medium
            setTextColor(CoreColors.black)
            setText(R.string.themes)
            fontSize(23f)
            layoutParams(linearLayoutParams().marginBottom(ctx.dp(8)))
        })

        val themeLayout = FrameLayout(ctx).apply {
            layoutParams(linearLayoutParams().marginBottom(ctx.dp(16)))
        }
        linear.addView(themeLayout)

        val themeManager = app.themeManager
        val theme = themeManager.theme()

        val sun = ThemeItemView(ctx).apply {
            changeIcon(R.drawable.ic_light_mode_24)
            changeIsChecked(theme == Theme.LIGHT)
        }
        themeLayout.addView(sun)

        val dark = ThemeItemView(ctx).apply {
            changeIcon(R.drawable.ic_dark_mode_24)
            changeIsChecked(theme == Theme.DARK)
        }
        themeLayout.addView(dark)

        sun.changeClickListener {
            dark.changeIsChecked(false)
            themeManager.light()
        }
        dark.changeClickListener {
            sun.changeIsChecked(false)
            themeManager.dark()
        }

        themeLayout.afterMeasure {
            val dimen = (themeLayout.measuredWidth / 2) - ctx.dp(4)
            sun.layoutParams(frameLayoutParams().width(dimen)
                .gravity(Gravity.START)
                .wrapHeight())
            dark.layoutParams(frameLayoutParams().width(dimen)
                .gravity(Gravity.END)
                .wrapHeight())
        }

        linear.addView(TextView(ctx).apply {
            typeface = opensans_medium
            setTextColor(CoreColors.black)
            setText(R.string.font_size)
            fontSize(23f)
            layoutParams(linearLayoutParams().marginBottom(ctx.dp(8)))
        })

        val placeholder = PlaceholderView(ctx)
        val savedFontSize = app.prefs.float("text_font_size", 23f)
        placeholder.changeFontSize(savedFontSize)

        val slider = SeekBar(ctx).apply {
            progressTintList = ColorStateList.valueOf(CoreColors.pink500)
            thumb = GradientDrawable().apply {
                setSize(ctx.dp(16), ctx.dp(16))
                cornerRadius = ctx.dp(50f)
                color = ColorStateList.valueOf(CoreColors.pink500)
            }

            progress = ((savedFontSize - 23f) * 10f).roundToInt()

            setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    val fontSize = 10 * (p1 / 100f) + 23f
                    placeholder.changeFontSize(fontSize)
                    app.prefs.saveFloat("text_font_size", fontSize)
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            padding(ctx.dp(8))
            layoutParams(linearLayoutParams().matchWidth().wrapHeight()
                .marginBottom(ctx.dp(8)))
        }
        linear.addView(slider, placeholder)
    }
}