package ru.freeit.evilbirth.chapter

import android.widget.TextView

class Chapter(
    private val id: Int,
    private val name: String
) {
    override fun toString(): String {
        return "$id. $name"
    }
    fun name(view: TextView) {
        view.text = "$id. $name"
    }
    fun id() = id

    fun click(clicker: (Int) -> Unit, view: TextView) {
        view.setOnClickListener { clicker.invoke(id) }
    }
}