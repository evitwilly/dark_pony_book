package ru.freeit.evilbirth.paragraph

import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.chapter.Chapter
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreLinearLayout
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layout.recyclerLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.padding
import ru.freeit.evilbirth.core.view.typeface.equestria_typeface
import ru.freeit.evilbirth.core.view.typeface.opensans_medium
import ru.freeit.evilbirth.core.view.typeface.opensans_regular
import ru.freeit.evilbirth.core.view.typeface.opensans_semibold

class FooterViewHolderViews(val linear: LinearLayout, val prev: TextView, val next: TextView)

class FooterViewHolder(private val views: FooterViewHolderViews) : RecyclerView.ViewHolder(views.linear) {

    fun bind(prev: Chapter?, next: Chapter?, onPrevClick: () -> Unit, onNextClick: () -> Unit) {
        if (prev != null) {
            prev.name(views.prev)
            views.prev.setOnClickListener { onPrevClick.invoke() }
        } else {
            views.prev.isVisible = false
        }

        if (next != null) {
            next.name(views.next)
            views.next.setOnClickListener { onNextClick.invoke() }
            if (prev != null) {
                views.prev.layoutParams = (views.prev.layoutParams as LinearLayout.LayoutParams).apply {
                    bottomMargin = views.prev.context.dp(16)
                }
            }
        } else {
            views.next.isVisible = false
        }
    }

    companion object {
        fun from(parent: ViewGroup) : FooterViewHolder {
            val ctx = parent.context
            val linearLayout = CoreLinearLayout(ctx).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams(recyclerLayoutParams()
                    .matchWidth().wrapHeight()
                    .marginTop(context.dp(12))
                    .marginBottom(context.dp(12)))
            }

            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = CoreColors.pink500
                style = Paint.Style.STROKE
                strokeWidth = ctx.dp(4f)
            }

            val prev = CoreTextView(parent.context).apply {
                fontSize(21f)
                typeface = opensans_semibold
                gravity = Gravity.START

                layoutParams(linearLayoutParams().matchWidth().wrapHeight())
            }
            linearLayout.addView(prev)

            val next = CoreTextView(parent.context).apply {
                fontSize(21f)
                typeface = opensans_semibold
                gravity = Gravity.END
                layoutParams(linearLayoutParams().matchWidth().wrapHeight())
            }
            linearLayout.addView(next)

            return FooterViewHolder(FooterViewHolderViews(linearLayout, prev, next))
        }
    }
}

class ParagraphViewHolder(private val text: TextView) : RecyclerView.ViewHolder(text) {

    fun bind(index: Int, content: String) {
        text.text = content

        if (index == 0) {
            val params = text.layoutParams as RecyclerView.LayoutParams
            params.topMargin = text.context.dp(16)
            text.layoutParams = params
        }
    }

    companion object {
        fun from(parent: ViewGroup, fontSize: Float) : ParagraphViewHolder {
            val text = CoreTextView(parent.context).apply {
                typeface = opensans_regular
                setTextColor(CoreColors.black)
                fontSize(fontSize)
                layoutParams(recyclerLayoutParams().matchWidth().wrapHeight().marginBottom(context.dp(12)))
            }
            return ParagraphViewHolder(text)
        }
    }
}