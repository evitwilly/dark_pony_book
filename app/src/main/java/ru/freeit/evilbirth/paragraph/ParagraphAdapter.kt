package ru.freeit.evilbirth.paragraph

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.chapter.Chapter
import ru.freeit.evilbirth.core.CoreNavigator

class ParagraphAdapter(
    private val paragraphs: List<String>,
    private val navigator: CoreNavigator,
    private val prev: Chapter?,
    private val next: Chapter?,
    private val fontSize: Float
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == paragraphs.size) 2 else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return if (viewType == 1) {
            ParagraphViewHolder.from(parent, fontSize)
        }  else {
            FooterViewHolder.from(parent)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ParagraphViewHolder) {
            holder.bind(position, paragraphs[position])
        } else if (holder is FooterViewHolder) {
            holder.bind(prev, next,
                onPrevClick = {
                    navigator.back()
                    navigator.navigate(ParagraphListFragment.newInstance(prev!!.id()))
                }, onNextClick = {
                    navigator.back()
                    navigator.navigate(ParagraphListFragment.newInstance(next!!.id()))
                })
        }
    }
    override fun getItemCount() = paragraphs.size + 1

}