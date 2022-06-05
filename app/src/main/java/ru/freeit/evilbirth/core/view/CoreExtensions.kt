package ru.freeit.evilbirth.core.view

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.view.layout.LP
import kotlin.math.roundToInt

fun ViewGroup.addView(vararg items: View) {
    for (item in items) {
        addView(item)
    }
}

fun View.afterMeasure(func: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            func()
        }
    })
}

fun gradientDrawable(
    color: Int = 0, width: Int = 0, height: Int = 0,
    topLeftCorner: Float = 0f, topRightCorner: Float = 0f,
    bottomRightCorner: Float = 0f, bottomLeftCorner: Float = 0f
) = GradientDrawable().apply {
    setColor(color)
    setSize(width, height)
    cornerRadii = floatArrayOf(
        topLeftCorner, topLeftCorner,
        topRightCorner, topRightCorner,
        bottomRightCorner, bottomRightCorner,
        bottomLeftCorner, bottomLeftCorner
    )
}

fun TextView.fontSize(sp: Float) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, sp)
}

fun RecyclerView.koeff() : Float {
    val maxScroll = computeVerticalScrollRange()
    val currentScroll = computeVerticalScrollOffset() + computeVerticalScrollExtent()
    return currentScroll / maxScroll.toFloat() // 0f..1f
}

fun Context.colorBy(@ColorRes resId: Int) : Int {
    return ContextCompat.getColor(this, resId)
}

fun Activity.dp(size: Int) = (resources.displayMetrics.density * size).roundToInt()
fun Context.dp(size: Int) = (resources.displayMetrics.density * size).roundToInt()
fun Context.dp(size: Float) = resources.displayMetrics.density * size

fun View.layoutParams(params: LP<*>) {
    layoutParams = params.build()
}

fun View.padding(start: Int = paddingStart, top: Int = paddingTop, end: Int = paddingEnd, bottom: Int = paddingBottom) {
    setPadding(start, top, end, bottom)
}

fun View.padding(horizontal: Int, vertical: Int) {
    padding(horizontal, vertical, horizontal, vertical)
}

fun View.padding(all: Int) {
    padding(all, all, all, all)
}