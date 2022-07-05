package ru.freeit.evilbirth.paragraph

import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.chapter.Chapter
import ru.freeit.evilbirth.core.view.components.CoreLinearLayout
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layout.recyclerLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.typeface.opensans_semibold


class FooterViewHolder(private val views: FooterViewHolderViews) : RecyclerView.ViewHolder(views.linear) {

    class FooterViewHolderViews(
        val linear: LinearLayout,
        val prev: TextView,
        val next: TextView
    )

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
                setPadding(0, context.dp(12), 0, context.dp(12))
                layoutParams(recyclerLayoutParams().matchWidth().wrapHeight())
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