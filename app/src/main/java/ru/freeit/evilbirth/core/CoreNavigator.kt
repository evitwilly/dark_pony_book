package ru.freeit.evilbirth.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.freeit.evilbirth.R

class CoreNavigator(private val fragmentManager: FragmentManager) {

    fun navigate(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.empty, R.anim.slide_right)
            .replace(fragmentContainerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun init(initialFragment: Fragment) {
        fragmentManager.beginTransaction()
            .add(fragmentContainerId, initialFragment)
            .commit()
    }

    fun back() {
        fragmentManager.popBackStack()
    }

    companion object {
        private const val fragmentContainerId = R.id.fragment_container
    }
}