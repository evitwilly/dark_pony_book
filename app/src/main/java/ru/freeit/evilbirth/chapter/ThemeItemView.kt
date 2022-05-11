package ru.freeit.evilbirth.chapter

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.StateSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.annotation.DrawableRes
import ru.freeit.evilbirth.core.view.addView
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.padding

class ThemeItemView(ctx: Context) : LinearLayout(ctx) {

    fun changeIcon(@DrawableRes res: Int) {
        imageView.setImageResource(res)
    }

    fun changeIsChecked(isChecked: Boolean) {
        if (isChecked) {
            bgDrawable.setStroke(context.dp(2), CoreColors.pink500)
            background = bgDrawable
            imageView.setColorFilter(CoreColors.pink500)
            radioButton.isChecked = true
        } else {
            bgDrawable.setStroke(context.dp(2), CoreColors.grey)
            background = bgDrawable
            imageView.setColorFilter(CoreColors.grey)
            radioButton.isChecked = false
        }
    }

    fun changeClickListener(listener: () -> Unit) {
        setOnClickListener {
            changeIsChecked(true)
            listener.invoke()
        }
    }

    private val imageView = ImageView(ctx).apply {
        adjustViewBounds = true
        layoutParams(linearLayoutParams().matchWidth().wrapHeight().marginBottom(ctx.dp(8)))
    }

    private val radioButton = RadioButton(ctx).apply {
        setOnTouchListener { _, _ ->
            true
        }
        buttonTintList = ColorStateList(
            arrayOf(
                intArrayOf(R.attr.state_checked),
                StateSet.WILD_CARD
            ),
            intArrayOf(CoreColors.pink500, CoreColors.grey)
        )
        layoutParams(
            linearLayoutParams().width(ctx.dp(32))
            .gravity(Gravity.CENTER_HORIZONTAL)
            .height(ctx.dp(32)))
    }

    private val bgDrawable = GradientDrawable().apply {
        cornerRadius = ctx.dp(16f)
    }

    init {
        isClickable = true
        orientation = VERTICAL
        bgDrawable.setStroke(ctx.dp(2), CoreColors.grey)
        background = bgDrawable
        padding(context.dp(16))
        addView(imageView, radioButton)
    }

}