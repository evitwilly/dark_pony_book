package ru.freeit.evilbirth.chapter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.core.data.BookData
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import ru.freeit.evilbirth.paragraph.ParagraphListFragment

class ChapterListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = requireActivity().supportFragmentManager

        changeMenuVisible(true)
        changeMenuIcon(R.drawable.ic_settings)
        onMenuClick {
            manager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment())
                .addToBackStack(null)
                .commit()
        }

        val navigator = requireActivity().supportFragmentManager
        val list = RecyclerView(ctx).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutParams(linearLayoutParams().matchWidth().wrapHeight().weight(1f))
            val chapters = BookData.chapters
            adapter = object: RecyclerView.Adapter<PonyChapterViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                    PonyChapterViewHolder.from(parent)
                override fun onBindViewHolder(holder: PonyChapterViewHolder, position: Int) {
                    holder.bind(position, chapters[position]) { id, name ->
                        navigator.beginTransaction()
                            .replace(
                                R.id.fragment_container,
                                ParagraphListFragment.newInstance(id)
                            )
                            .addToBackStack(null)
                            .commit()
                    }
                }
                override fun getItemCount() = chapters.size

            }
        }
        addViewToRoot(list)
    }
}