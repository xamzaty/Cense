package com.cense.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.cense.utils.ViewExtensions.goneIf
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

object TextViewExtensions {

    /**
     * Extension-функции для настроек ввода (установки лимитов, допустимых и запрещённых символов и т.д.)
     */

    fun EditText?.getPhoneNumber(): String = this?.text?.toString()?.trim()
        ?.replace("+", "")
        ?.replace("(", "")
        ?.replace(")", "")
        ?.replace(" ", "") ?: ""

    /**
    Mask examples:
    International phone numbers: +1 ([000]) [000] [00] [00]
    Local phone numbers: ([000]) [000]-[00]-[00]
    Names: [A][-----------------------------------------------------]
    Text: [A…]
    Dates: [00]{.}[00]{.}[9900]
    Serial numbers: [AA]-[00000099]
    IPv4: [099]{.}[099]{.}[099]{.}[099]
    Visa card numbers: [0000] [0000] [0000] [0000]
    MM/YY: [00]{/}[00]
    UK IBAN: GB[00] [____] [0000] [0000] [0000] [00]
     * */
    fun EditText.setMask(affineFormat: String, block: (String) -> Unit) {
        installOn(
            this,
            affineFormat,
            listOf(affineFormat), AffinityCalculationStrategy.PREFIX,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    block.invoke(formattedValue)
                }
            }
        )
    }

    fun EditText.onTextChange(block: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                block.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                // do nothing
            }
        })
    }

    /**
     * Смена цвета элементов [EditText]
     *
     * @param textColorRes цвет текста
     * @param hintColorRes цвет хинта
     */
    fun EditText.setTextColors(@ColorRes textColorRes: Int, @ColorRes hintColorRes: Int) {
        this.setTextColor(ContextCompat.getColor(this.context, textColorRes))
        this.setHintTextColor(ContextCompat.getColor(this.context, hintColorRes))
    }

    /**
     * Убирает подчеркивание с конкретного EditText
     *
     * @param shouldDisabled - флаг отключения
     */
    fun EditText.removeUnderline(disableUnderline: Boolean) {
        if (disableUnderline) {
            background = null
        }
    }

    /**
     * Возврат к дефолтному фону для EditText (возвращает подчеркивание)
     */
    fun EditText.resetToDefaultBackground() {
        background = ContextCompat.getDrawable(context, android.R.drawable.edit_text)
    }

    /**
     * Устанавливает курсор в конец EditText'а
     */
    fun EditText.selectionToEnd() {
        if (text.isNotEmpty()) {
            setSelection(text.length)
        }
    }

    /**
     * Установка текста.
     *
     * Если текст пустой - [TextView] скрывается.
     */
    fun TextView.setTextOrGone(text: String?) {
        goneIf(text.isNullOrBlank())
        setText(text)
    }
}