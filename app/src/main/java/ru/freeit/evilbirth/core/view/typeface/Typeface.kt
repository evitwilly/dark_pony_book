package ru.freeit.evilbirth.core.view.typeface

import android.graphics.Typeface
import android.view.View

private val typefaceCache = hashMapOf<String, Typeface>()

private const val woodrowFileName = "Woodrow_Cyrillic.ttf"
val View.woodrow_typeface: Typeface
    get() {
        if (!typefaceCache.containsKey(woodrowFileName)) {
            val font = Typeface.createFromAsset(resources.assets, woodrowFileName)
            typefaceCache[woodrowFileName] = font
        }
        return typefaceCache[woodrowFileName]!!
    }

private const val equestriaFileName = "Equestria_Cyrillic.ttf"
val View.equestria_typeface: Typeface
    get() {
        if (!typefaceCache.containsKey(equestriaFileName)) {
            val font = Typeface.createFromAsset(resources.assets, equestriaFileName)
            typefaceCache[equestriaFileName] = font
        }
        return typefaceCache[equestriaFileName]!!
    }

private const val openSansRegular = "OpenSans_Regular.ttf"
val View.opensans_regular: Typeface
    get() {
        if (!typefaceCache.containsKey(openSansRegular)) {
            val font = Typeface.createFromAsset(resources.assets, openSansRegular)
            typefaceCache[openSansRegular] = font
        }
        return typefaceCache[openSansRegular]!!
    }

private const val openSansMedium = "OpenSans-Medium.ttf"
val View.opensans_medium: Typeface
    get() {
        if (!typefaceCache.containsKey(openSansMedium)) {
            val font = Typeface.createFromAsset(resources.assets, openSansMedium)
            typefaceCache[openSansMedium] = font
        }
        return typefaceCache[openSansMedium]!!
    }

private const val openSansSemiBold = "OpenSans-SemiBold.ttf"
val View.opensans_semibold: Typeface
    get() {
        if (!typefaceCache.containsKey(openSansSemiBold)) {
            val font = Typeface.createFromAsset(resources.assets, openSansSemiBold)
            typefaceCache[openSansSemiBold] = font
        }
        return typefaceCache[openSansSemiBold]!!
    }


