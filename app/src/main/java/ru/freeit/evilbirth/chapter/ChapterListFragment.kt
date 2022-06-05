package ru.freeit.evilbirth.chapter

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.evilbirth.R
import ru.freeit.evilbirth.core.data.BookData
import ru.freeit.evilbirth.core.view.layout.linearLayoutParams
import ru.freeit.evilbirth.core.view.layoutParams

class ChapterListFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeMenuVisible(true)
        changeMenuIcon(R.drawable.ic_settings)
        onMenuClick { navigator.navigate(SettingsFragment()) }

        val list = RecyclerView(ctx).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ChapterAdapter(BookData.chapters, navigator)
            layoutParams(linearLayoutParams().matchWidth().wrapHeight().weight(1f))
        }
        addViewToRoot(list)
    }
}