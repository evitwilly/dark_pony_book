package ru.freeit.evilbirth.paragraph

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.recyclerLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.typeface.opensans_regular


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
                setPadding(0, 0, 0, context.dp(12))
                layoutParams(recyclerLayoutParams().matchWidth().wrapHeight())
            }
            return ParagraphViewHolder(text)
        }
    }
}