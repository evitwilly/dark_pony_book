package ru.freeit.evilbirth.paragraph

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.core.data.BookData
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.chapter.BaseFragment
import ru.freeit.evilbirth.core.view.*
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layout.vertical
import ru.freeit.evilbirth.core.view.typeface.woodrow_typeface

class ParagraphListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        val chapter = BookData.chapters[args.getInt(idKey, 1) - 1]
        changeTitle(chapter.toString())

        val paragraphs = BookData.paragraphs[args.getInt(idKey, 1)]
        val fontSize = app.prefs.float("text_font_size", 23f)
        val list = RecyclerView(ctx).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutParams(linearLayoutParams().matchWidth().wrapHeight().weight(1f).margins(context.dp(16)))
            adapter = object: RecyclerView.Adapter<ParagraphViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                    ParagraphViewHolder.from(parent, fontSize)
                override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
                    holder.bind(paragraphs[position])
                }
                override fun getItemCount() = paragraphs.size

            }
        }
        addViewToRoot(list)
    }

    companion object {
        private const val idKey = "chapter_id"

        fun newInstance(id: Int, name: String) = ParagraphListFragment().apply {
            arguments = bundleOf(idKey to id)
        }
    }
}