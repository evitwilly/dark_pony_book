package ru.freeit.evilbirth.core.view.layout

import androidx.recyclerview.widget.RecyclerView

private const val match = RecyclerView.LayoutParams.MATCH_PARENT
private const val wrap = RecyclerView.LayoutParams.WRAP_CONTENT

class RecyclerViewLP(private val params : RecyclerView.LayoutParams = RecyclerView.LayoutParams(wrap, wrap))
    : AbstractMarginLP<RecyclerView.LayoutParams, RecyclerViewLP>(params, match, wrap), LP<RecyclerView.LayoutParams> {

    override fun build() = params
    override fun with(params: RecyclerView.LayoutParams) = RecyclerViewLP(params)

}