package ru.freeit.evilbirth.settings

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.frameLayoutParams
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layout.vertical
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.padding
import ru.freeit.evilbirth.core.view.typeface.woodrow_typeface

class PlaceholderView(ctx: Context) : LinearLayout(ctx) {

    fun changeFontSize(size: Float) {
        placeholderView.fontSize(size)
    }

    private val placeholderView = CoreTextView(ctx).apply {
        text = "...\nВ отличии от Лары, Флурри бывала там гораздо чаще, чтобы практиковаться с Санберстом контролировать свои уникальные магические способности.\n..."
        fontSize(23f)
        padding(ctx.dp(16))
    }

    init {
        vertical()
        padding(bottom = ctx.dp(8))

        val frameLayout = FrameLayout(ctx).apply {
            elevation = ctx.dp(4f)
            background = GradientDrawable().apply {
                cornerRadii = floatArrayOf(
                    ctx.dp(8f), ctx.dp(8f),
                    ctx.dp(8f), ctx.dp(8f),
                    0f, 0f, 0f, 0f
                )
                setColor(CoreColors.white)
            }
            padding(horizontal = ctx.dp(16), vertical = ctx.dp(8))
            layoutParams(linearLayoutParams().matchWidth().wrapHeight()
                .margins(ctx.dp(1))
                .marginBottom(ctx.dp(8)))
        }
        addView(frameLayout)

        val text = TextView(ctx).apply {
            fontSize(18f)
            text = "Летние каникулы"
            setTextColor(CoreColors.black)
            typeface = woodrow_typeface
            layoutParams(
                frameLayoutParams().gravity(Gravity.START or Gravity.CENTER_VERTICAL)
                .marginEnd(ctx.dp(32)).marginStart(ctx.dp(40)))
        }
        frameLayout.addView(text)

        val back = FrameLayout(ctx).apply {
            background = RippleDrawable(
                ColorStateList.valueOf(CoreColors.pink700),
                GradientDrawable().apply {
                    setColor(CoreColors.white)
                    cornerRadius = ctx.dp(50f)
                }, null
            )
            addView(ImageView(ctx).apply {
                setImageResource(R.drawable.ic_back)
                layoutParams(frameLayoutParams().width(ctx.dp(24))
                    .height(ctx.dp(24)).gravity(Gravity.CENTER))
            })
            layoutParams(frameLayoutParams().width(ctx.dp(32)).height(ctx.dp(32))
                .gravity(Gravity.START or Gravity.CENTER_VERTICAL))
        }
        frameLayout.addView(back)

        addView(placeholderView)

        background = GradientDrawable().apply {
            cornerRadius = ctx.dp(8f)
            setStroke(ctx.dp(1), CoreColors.grey)
        }
    }
}