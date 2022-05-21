package ru.freeit.evilbirth.chapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.fontSize
import ru.freeit.evilbirth.core.view.layout.recyclerLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.core.view.padding
import ru.freeit.evilbirth.core.view.typeface.equestria_typeface

class PonyChapterViewHolder(private val text: TextView) : RecyclerView.ViewHolder(text) {

    fun bind(index: Int, chapter: Chapter, clicker: (Int, String) -> Unit) {
        chapter.name(text)
        chapter.click(clicker, text)

        if (index == 0) {
            val params = text.layoutParams as RecyclerView.LayoutParams
            params.topMargin = text.context.dp(16)
            text.layoutParams = params
        }
    }

    companion object {
        fun from(parent: ViewGroup) : PonyChapterViewHolder {
            val ctx = parent.context
            val text = object: CoreTextView(parent.context) {
                override fun changePropertiesByTheme(self: CoreTextView, theme: Theme) {
                    if (theme == Theme.LIGHT) {
                        self.setTextColor(CoreColors.pink500)
                        self.background = ChapterRippleDrawable(ctx, CoreColors.pink500)
                    } else {
                        self.setTextColor(CoreColors.white)
                        self.background = ChapterRippleDrawable(ctx, CoreColors.white)
                    }
                }
            }.apply {
                isClickable = true
                fontSize(23f)
                padding(context.dp(16))
                typeface = equestria_typeface
                layoutParams(recyclerLayoutParams().matchWidth().wrapHeight()
                    .margins(context.dp(8)))
            }
            return PonyChapterViewHolder(text)
        }
    }
}