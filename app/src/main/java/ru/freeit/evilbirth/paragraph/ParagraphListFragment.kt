package ru.freeit.evilbirth.paragraph

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.zhanghai.android.fastscroll.FastScrollNestedScrollView
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import ru.freeit.evilbirth.chapter.BaseFragment
import ru.freeit.evilbirth.core.data.BookData
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreFrameLayout
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.gradientDrawable
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams

class ParagraphListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        val chapter = BookData.chapters[args.getInt(idKey, 0)]
        changeTitle(chapter.toString())

        val frame = CoreFrameLayout(ctx)

        val chapterId = args.getInt(idKey, 1)
        val paragraphs = BookData.paragraphs[chapterId]

        val prev = BookData.chapters.getOrNull(chapterId - 1)
        val next = BookData.chapters.getOrNull(chapterId + 1)

        val fontSize = app.prefs.float("text_font_size", 23f)

        val nested = FastScrollNestedScrollView(ctx).apply {
            layoutParams(linearLayoutParams().matchWidth().wrapHeight().weight(1f))
        }
        frame.addView(nested)

        val list = RecyclerView(ctx).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ParagraphAdapter(paragraphs, navigator, prev, next, fontSize)
            isNestedScrollingEnabled = true
            val dimen1 = ctx.dp(16)
            setPadding(dimen1, 0, dimen1, dimen1)
        }
        nested.addView(list)

        val builder = FastScrollerBuilder(nested).setThumbDrawable(
            gradientDrawable(
                color = CoreColors.pink500,
                width = ctx.dp(8),
                height = ctx.dp(32),
                topLeftCorner = ctx.dp(2f),
                bottomLeftCorner = ctx.dp(2f)
            )
        ).setTrackDrawable(
            gradientDrawable(
                color = CoreColors.transparent,
                width = ctx.dp(8)
            )
        )
        builder.disableScrollbarAutoHide()
        builder.build()

        addViewToRoot(frame)
    }

    companion object {
        private const val idKey = "chapter_id"

        fun newInstance(id: Int) = ParagraphListFragment().apply {
            arguments = bundleOf(idKey to id)
        }
    }

}