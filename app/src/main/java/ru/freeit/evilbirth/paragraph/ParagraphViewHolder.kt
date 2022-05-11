package ru.freeit.evilbirth.paragraph

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.recyclerLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.typeface.equestria_typeface
import ru.freeit.evilbirth.core.view.typeface.opensans_regular

class ParagraphViewHolder(private val text: TextView) : RecyclerView.ViewHolder(text) {

    fun bind(content: String) {
        text.text = content
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