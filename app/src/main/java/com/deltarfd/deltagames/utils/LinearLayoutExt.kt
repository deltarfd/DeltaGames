package com.deltarfd.deltagames.utils

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.deltarfd.deltagames.R

fun LinearLayout.addPlatformIcons(@DrawableRes vararg icons: Int) {
    icons.forEach { res ->
        addView(ImageView(context).also {
            it.setImageResource(res)
            it.setPadding(0, 0, context.resources.getDimensionPixelSize(R.dimen._6sdp), 0)
        })
    }
}