package ru.freeit.evilbirth.core.view.layout

import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat

private const val match = LinearLayoutCompat.LayoutParams.MATCH_PARENT
private const val wrap = LinearLayoutCompat.LayoutParams.WRAP_CONTENT

class LinearLayoutLP(private var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(match, wrap))
    : AbstractMarginLP<LinearLayout.LayoutParams, LinearLayoutLP>(params, match, wrap), LP<LinearLayout.LayoutParams> {

    fun weight(w: Float) = LinearLayoutLP(params.apply { weight = w })
    fun gravity(grav: Int) = LinearLayoutLP(params.apply { gravity = grav })

    override fun with(params: LinearLayout.LayoutParams) = LinearLayoutLP(params)
    override fun build() = params

}