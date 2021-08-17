package com.cense.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.cense.utils.ViewExtensions.goneIf

object ImageViewExtensions {

    /**
     * Extension-функции для работы с ImageView.
     */

    /**
     * Установка изображения из ресурсов или скрытие [ImageView] если ([drawable] == null).
     */
    fun ImageView.setImageDrawableOrGone(drawable: Drawable?) {
        goneIf(drawable == null)
        setImageDrawable(drawable)
    }
}