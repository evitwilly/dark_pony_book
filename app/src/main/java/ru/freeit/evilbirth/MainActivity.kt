package ru.freeit.evilbirth

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import ru.freeit.evilbirth.chapter.ChapterListFragment
import ru.freeit.evilbirth.core.view.colors.CoreColors


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = CoreColors.pink700

        val frameLayout = FrameLayout(this)
        frameLayout.id = R.id.fragment_container

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ChapterListFragment())
                .commit()
        }

        setContentView(frameLayout)
    }

}