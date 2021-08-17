package com.cense.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorInt

object ViewExtensions {

    fun View.show(show: Boolean = true) {
        if (show) visibility = View.VISIBLE
        else hide()
    }

    fun View.hide() {
        visibility = View.GONE
    }

    fun showGroupViews(vararg view: View) = view.forEach { it.show() }

    fun hideGroupViews(vararg view: View) = view.forEach { it.hide() }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun View.toast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Делает вью невидимой и задизейбленной, если выполняется условие
     */
    fun View.invisibleIf(invisible: Boolean) {
        if (invisible) {
            visibility = View.INVISIBLE
            isEnabled = false
        } else {
            visibility = View.VISIBLE
            isEnabled = true
        }
    }

    /**
     * Cкрывает вью , если выполняется условие
     */
    fun View.goneIf(gone: Boolean) {
        if (gone) {
            visibility = View.GONE
            isEnabled = false
        } else {
            visibility = View.VISIBLE
            isEnabled = true
        }
    }

    /**
     * Меняет цвет background у view
     */
    fun View.changeViewBackgroundColor(@ColorInt color: Int) {
        when (val background = background.mutate()) {
            is ShapeDrawable -> background.paint.color = color
            is GradientDrawable -> background.setColor(color)
            is ColorDrawable -> background.color = color
        }
    }

    fun View.dpToPixel(dp: Float): Float =
        dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun View.dpToPixel(dp: Int): Int =
        dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()

    fun View.dpToPixelInt(dp: Float): Int =
        (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()

    fun View.pixelToDp(px: Float): Float =
        px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun View.spToPx(sp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        ).toInt()
    }

    fun View.spToPx(sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            context.resources.displayMetrics
        )
    }
}