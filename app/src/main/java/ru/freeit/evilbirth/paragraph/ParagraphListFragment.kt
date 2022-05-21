package ru.freeit.evilbirth.paragraph

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.chapter.BaseFragment
import ru.freeit.evilbirth.core.data.BookData
import ru.freeit.evilbirth.core.view.afterMeasure
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreFrameLayout
import ru.freeit.evilbirth.core.view.dp
import ru.freeit.evilbirth.core.view.koeff
import ru.freeit.evilbirth.core.view.layout.frameLayoutParams
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams
import kotlin.math.abs
import kotlin.math.roundToInt

class ParagraphListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        val chapter = BookData.chapters[args.getInt(idKey, 1) - 1]
        changeTitle(chapter.toString())

        val frame = CoreFrameLayout(ctx)

        val chapterId = args.getInt(idKey, 1)
        val paragraphs = BookData.paragraphs[chapterId]

        val prev = BookData.chapters.getOrNull(chapterId - 2)
        val next = BookData.chapters.getOrNull(chapterId)

        val fontSize = app.prefs.float("text_font_size", 23f)
        val navigator = requireActivity().supportFragmentManager
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val list = RecyclerView(ctx).apply {
            layoutManager = manager
            val sixteenDp = context.dp(16)
            layoutParams(linearLayoutParams().matchWidth().wrapHeight().weight(1f).margins(
                sixteenDp, 0, sixteenDp, sixteenDp
            ))
            adapter = object: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
                                navigator.popBackStack()
                                navigator.beginTransaction()
                                    .replace(R.id.fragment_container, newInstance(
                                        prev!!.id()
                                    ))
                                    .addToBackStack(null)
                                    .commit()
                            }, onNextClick = {
                                navigator.popBackStack()
                                navigator.beginTransaction()
                                    .replace(R.id.fragment_container, newInstance(
                                        next!!.id()
                                    ))
                                    .addToBackStack(null)
                                    .commit()
                            })
                    }
                }
                override fun getItemCount() = paragraphs.size + 1

            }
        }
        frame.addView(list)

        var initialKoeff = 0f
        fun scaleVerticalOffset() : Int {
            val currentKoeff = list.koeff() - initialKoeff

            val height = frame.measuredHeight + ctx.dp(32)

            if (currentKoeff < 0) return 0
            return (height * currentKoeff).roundToInt()
        }

        val sliderView = View(ctx).apply {
            setBackgroundColor(CoreColors.pink500)
            afterMeasure {
                initialKoeff = list.koeff()
                layoutParams = layoutParams.apply {
                    height = scaleVerticalOffset()
                }
            }
            layoutParams(frameLayoutParams().gravity(Gravity.END or Gravity.TOP).width(ctx.dp(3)).matchHeight())
        }
        frame.addView(sliderView)

        list.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sliderView.layoutParams = sliderView.layoutParams.apply {
                    height = scaleVerticalOffset()
                }
            }
        })

        addViewToRoot(frame)
    }

    companion object {
        private const val idKey = "chapter_id"

        fun newInstance(id: Int) = ParagraphListFragment().apply {
            arguments = bundleOf(idKey to id)
        }
    }
}