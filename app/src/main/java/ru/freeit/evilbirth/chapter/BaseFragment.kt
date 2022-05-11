package ru.freeit.evilbirth.chapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.core.App
import ru.freeit.evilbirth.core.theme.Theme
import ru.freeit.evilbirth.core.view.*
import ru.freeit.evilbirth.core.view.colors.CoreColors
import ru.freeit.evilbirth.core.view.components.CoreFrameLayout
import ru.freeit.evilbirth.core.view.components.CoreImageView
import ru.freeit.evilbirth.core.view.components.CoreLinearLayout
import ru.freeit.evilbirth.core.view.components.CoreTextView
import ru.freeit.evilbirth.core.view.layout.frameLayoutParams
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layout.vertical
import ru.freeit.evilbirth.core.view.typeface.woodrow_typeface

abstract class BaseFragment : Fragment() {

    private var root: ViewGroup? = null
    private var titleView: TextView? = null
    private var menuView: FrameLayout? = null
    private var menuImage: ImageView? = null

    protected val app by lazy { requireActivity().application as App }
    protected val ctx by lazy { requireContext() }

    fun addViewToRoot(view: View) {
        root?.addView(view)
    }

    fun changeTitle(title: String) {
        titleView?.text = title
    }

    fun changeMenuIcon(@DrawableRes res: Int) {
        menuImage?.setImageResource(res)
    }

    fun changeMenuVisible(isVisible: Boolean) {
        menuView?.isVisible = isVisible
    }

    fun onMenuClick(click: () -> Unit) {
        menuView?.setOnClickListener { click.invoke() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val ctx = inflater.context

        val linear = CoreLinearLayout(ctx).apply {
            vertical()
        }
        root = linear

        val frameLayout = CoreFrameLayout(ctx).apply {
            elevation = ctx.dp(4f)
            background = GradientDrawable().apply {
                setColor(CoreColors.white)
            }
            padding(horizontal = ctx.dp(16), vertical = ctx.dp(8))
            layoutParams(linearLayoutParams().matchWidth().wrapHeight()
                .marginBottom(ctx.dp(8)))
        }
        linear.addView(frameLayout)

        val manager = requireActivity().supportFragmentManager
        val fragment = manager.findFragmentById(R.id.fragment_container)
        val isBack = fragment !is ChapterListFragment

        val text = CoreTextView(ctx).apply {
            fontSize(25f)
            setText(R.string.birth_of_evil)
            setTextColor(CoreColors.black)
            typeface = woodrow_typeface
            val params = frameLayoutParams().gravity(Gravity.START or Gravity.CENTER_VERTICAL)
                .marginEnd(ctx.dp(32))
            if (isBack) {
                layoutParams(params.marginStart(ctx.dp(40)))
            }
            layoutParams(params)
        }
        this.titleView = text
        frameLayout.addView(text)

        val back = object: CoreFrameLayout(ctx) {
            override fun changePropertiesByTheme(self: CoreFrameLayout, theme: Theme) {
                self.background = RippleDrawable(
                    ColorStateList.valueOf(CoreColors.pink700),
                    GradientDrawable().apply {
                        if (theme == Theme.LIGHT) {
                            setColor(CoreColors.white)
                        } else {
                            setColor(CoreColors.black)
                        }
                        cornerRadius = ctx.dp(50f)
                    }, null
                )
            }
        }.apply {
            isClickable = true

            addView(CoreImageView(ctx).apply {
                setImageResource(R.drawable.ic_back)
                layoutParams(frameLayoutParams().width(ctx.dp(24))
                    .height(ctx.dp(24)).gravity(Gravity.CENTER))
            })
            layoutParams(frameLayoutParams().width(ctx.dp(32)).height(ctx.dp(32))
                .gravity(Gravity.START or Gravity.CENTER_VERTICAL))
        }
        back.setOnClickListener { manager.popBackStack() }
        back.isVisible = isBack
        frameLayout.addView(back)

        val settings = object: CoreFrameLayout(ctx) {
            override fun changePropertiesByTheme(self: CoreFrameLayout, theme: Theme) {
                self.background = RippleDrawable(
                    ColorStateList.valueOf(CoreColors.pink700),
                    GradientDrawable().apply {
                        if (theme == Theme.LIGHT) {
                            setColor(CoreColors.white)
                        } else {
                            setColor(CoreColors.black)
                        }
                        cornerRadius = ctx.dp(50f)
                    }, null
                )
            }
        }.apply {
            isClickable = true
            isVisible = false
            val imageView = CoreImageView(ctx).apply {
                layoutParams(frameLayoutParams().width(ctx.dp(24))
                    .height(ctx.dp(24)).gravity(Gravity.CENTER))
            }
            addView(imageView)
            menuImage = imageView
            layoutParams(frameLayoutParams().width(ctx.dp(32)).height(ctx.dp(32))
                .gravity(Gravity.END or Gravity.CENTER_VERTICAL))
        }
        this.menuView = settings
        frameLayout.addView(settings)

        return linear
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.root = null
        this.titleView = null
        this.menuView = null
        this.menuImage = null
    }
}