package ru.freeit.evilbirth.core.view.layout

import android.widget.FrameLayout

private const val match = FrameLayout.LayoutParams.MATCH_PARENT
private const val wrap = FrameLayout.LayoutParams.WRAP_CONTENT

class FrameLayoutLP(private val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(match, wrap))
    : AbstractMarginLP<FrameLayout.LayoutParams, FrameLayoutLP>(params, match, wrap), LP<FrameLayout.LayoutParams> {

    fun gravity(grav: Int) = FrameLayoutLP(params.apply { gravity = grav })

    override fun with(params: FrameLayout.LayoutParams): FrameLayoutLP = FrameLayoutLP(params)

    override fun build() = params

}