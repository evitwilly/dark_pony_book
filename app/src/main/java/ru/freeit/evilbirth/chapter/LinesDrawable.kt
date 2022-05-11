package ru.freeit.evilbirth.chapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.dp

class LinesDrawable(ctx: Context) : Drawable() {

    private val size1 = ctx.dp(25f)
    private val size2 = ctx.dp(10f)
    private val size4 = ctx.dp(50f)

    private val paint = Paint().apply {
        color = CoreColors.pink700
        style = Paint.Style.FILL
        strokeWidth = size1
    }
    override fun draw(canvas: Canvas) {
        var startX = size1
        var endX = startX + size1

        val max = canvas.width - size1
        val size3 = canvas.height + size2
        while (endX < max) {
            canvas.drawLine(endX, -size2, startX, size3, paint)
            startX += size4
            endX = startX + size1
        }
    }
    override fun setAlpha(p0: Int) {}
    override fun setColorFilter(p0: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}