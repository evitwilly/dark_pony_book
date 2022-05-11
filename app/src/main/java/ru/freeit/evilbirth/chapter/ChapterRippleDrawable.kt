package ru.freeit.evilbirth.chapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.dp

class ChapterRippleDrawable(ctx: Context, primaryColor: Int) : RippleDrawable(
    ColorStateList.valueOf(CoreColors.pink700),
    object: Drawable() {
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = primaryColor
            style = Paint.Style.STROKE
            strokeWidth = ctx.dp(4f)
        }
        override fun draw(canvas: Canvas) {
            val width = canvas.width * 1f
            val height = canvas.height * 1f

            val waveWidth = ctx.dp(24f)

            canvas.drawPath(Path().apply {
                moveTo(waveWidth, height)
                cubicTo(waveWidth, height, -waveWidth, height / 2f, waveWidth, 0f)
                cubicTo(waveWidth, 0f, width - waveWidth, 0f, width - waveWidth, ctx.dp(16f))
                quadTo(width - waveWidth, ctx.dp(16f), width, height)
                lineTo(waveWidth, height)
            }, paint)
        }
        override fun setAlpha(p0: Int) {}
        override fun setColorFilter(p0: ColorFilter?) {}
        override fun getOpacity(): Int { return PixelFormat.OPAQUE
        }
    },
    object: Drawable() {
        private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = primaryColor
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = ctx.dp(4f)
        }
        override fun draw(canvas: Canvas) {
            val width = canvas.width * 1f
            val height = canvas.height * 1f

            val waveWidth = ctx.dp(24f)

            canvas.drawPath(Path().apply {
                moveTo(waveWidth, height)
                cubicTo(waveWidth, height, -waveWidth, height / 2f, waveWidth, 0f)
                cubicTo(waveWidth, 0f, width - waveWidth, 0f, width - waveWidth, ctx.dp(16f))
                quadTo(width - waveWidth, ctx.dp(16f), width, height)
                //cubicTo(width - waveWidth, ctx.dp(16f), width, ctx.dp(16f), width, height)
                lineTo(waveWidth, height)
            }, maskPaint)
        }
        override fun setAlpha(p0: Int) {}
        override fun setColorFilter(p0: ColorFilter?) {}
        override fun getOpacity(): Int { return PixelFormat.TRANSPARENT
        }
    }
)