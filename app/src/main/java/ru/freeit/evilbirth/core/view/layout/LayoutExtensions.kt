package ru.freeit.evilbirth.core.view.layout

import android.widget.LinearLayout

fun linearLayoutParams() = LinearLayoutLP()
fun frameLayoutParams() = FrameLayoutLP()
fun recyclerLayoutParams() = RecyclerViewLP()

fun LinearLayout.vertical() {
    orientation = LinearLayout.VERTICAL
}
fun LinearLayout.horizontal() {
    orientation = LinearLayout.HORIZONTAL
}
