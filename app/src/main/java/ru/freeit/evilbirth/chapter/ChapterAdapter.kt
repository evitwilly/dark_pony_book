package ru.freeit.evilbirth.chapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.CoreNavigator
import ru.freeit.evilbirth.paragraph.ParagraphListFragment

class ChapterAdapter(
    private val chapters: List<Chapter>,
    private val navigator: CoreNavigator
) : RecyclerView.Adapter<PonyChapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PonyChapterViewHolder.from(parent)
    override fun onBindViewHolder(holder: PonyChapterViewHolder, position: Int) {
        holder.bind(position, chapters[position]) { id ->
            navigator.navigate(ParagraphListFragment.newInstance(id))
        }
    }
    override fun getItemCount() = chapters.size
}