package com.mike.core.util

import android.os.SystemClock
import android.view.View


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * Just a validation to prevent doble click on a view element
 */
open class SafeClickListener(
    private var defaultInterval: Int = 500,
    private val onSafeClick: (View) -> Unit,
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) return
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }

}

fun View.setSafeOnClickListener(onSafeClick: ((View) -> Unit)?) {
    val safeClickListener = SafeClickListener {
        if (onSafeClick != null) {
            onSafeClick(it)
        }
    }
    setOnClickListener(
        if (onSafeClick != null) {
            safeClickListener
        } else {
            null
        }
    )
}

