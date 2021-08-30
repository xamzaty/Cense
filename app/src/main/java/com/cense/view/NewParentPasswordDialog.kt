package com.cense.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import com.cense.R
import com.cense.utils.Constants
import com.cense.utils.FragmentExtensions.showSnackbar
import com.cense.utils.FragmentExtensions.showToast
import com.cense.utils.ViewExtensions.hideKeyboard
import com.cense.utils.ViewExtensions.showKeyboard

class NewParentPasswordDialog : DialogFragment() {

    private lateinit var etNewNumberOne: EditText
    private lateinit var etNewNumberTwo: EditText
    private lateinit var etNewNumberThree: EditText
    private lateinit var etNewNumberFour: EditText

    private lateinit var etRepeatNumberOne: EditText
    private lateinit var etRepeatNumberTwo: EditText
    private lateinit var etRepeatNumberThree: EditText
    private lateinit var etRepeatNumberFour: EditText

    private lateinit var tvNewPasswordDescription: AppCompatTextView

    private lateinit var repeatPasswordLayout: LinearLayoutCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner_dialog);
        return inflater.inflate(R.layout.dialog_new_parent_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNewNumberOne = view.findViewById(R.id.edit_text_new_number_one_password)
        etNewNumberTwo = view.findViewById(R.id.edit_text_new_number_two_password)
        etNewNumberThree = view.findViewById(R.id.edit_text_new_number_three_password)
        etNewNumberFour = view.findViewById(R.id.edit_text_new_number_four_password)

        etRepeatNumberOne = view.findViewById(R.id.edit_text_repeat_number_one_password)
        etRepeatNumberTwo = view.findViewById(R.id.edit_text_repeat_number_two_password)
        etRepeatNumberThree = view.findViewById(R.id.edit_text_repeat_number_three_password)
        etRepeatNumberFour = view.findViewById(R.id.edit_text_repeat_number_four_password)

        tvNewPasswordDescription = view.findViewById(R.id.text_view_new_password_description)

        repeatPasswordLayout = view.findViewById(R.id.layout_repeat_password)

        etNewNumberOne.requestFocus()
        etNewNumberOne.showKeyboard()

        otpController()
    }

    private fun otpController() {

        etNewNumberOne.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                etNewNumberOne.requestFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (etNewNumberOne.text.isNullOrBlank()) {
                    etNewNumberOne.requestFocus()
                } else {
                    etNewNumberTwo.requestFocus()
                }
            }
        })

        etNewNumberTwo.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (etNewNumberTwo.text.isNullOrBlank()) {
                    etNewNumberOne.requestFocus()
                } else {
                    etNewNumberThree.requestFocus()
                }
            }
        })

        etNewNumberThree.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (etNewNumberThree.text.isNullOrBlank()) {
                    etNewNumberTwo.requestFocus()
                } else {
                    etNewNumberFour.requestFocus()
                }
            }
        })

        etNewNumberFour.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if (etNewNumberOne.text.isNullOrBlank() && etNewNumberTwo.text.isNullOrBlank() && etNewNumberThree.text.isNullOrBlank() && etNewNumberFour.text.isNullOrBlank()) {
                    showToast("Error!", false)
                } else if (etNewNumberFour.text.isNullOrBlank()) {
                    etNewNumberThree.requestFocus()
                } else {
                    repeatPasswordLayout.visibility = View.VISIBLE
                    tvNewPasswordDescription.visibility = View.GONE
                    etRepeatNumberOne.requestFocus()
                }
            }
        })

        etRepeatNumberOne.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etRepeatNumberOne.text.isNullOrBlank()) {
                    repeatPasswordLayout.visibility = View.GONE
                    etNewNumberFour.requestFocus()
                } else {
                    etRepeatNumberTwo.requestFocus()
                }
            }
        })

        etRepeatNumberTwo.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etRepeatNumberTwo.text.isNullOrBlank()) {
                    etRepeatNumberOne.requestFocus()
                } else {
                    etRepeatNumberThree.requestFocus()
                }
            }
        })

        etRepeatNumberThree.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etRepeatNumberThree.text.isNullOrBlank()) {
                    etRepeatNumberTwo.requestFocus()
                } else {
                    etRepeatNumberFour.requestFocus()
                }
            }
        })

        etRepeatNumberFour.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {

                if (etRepeatNumberOne.text.isNullOrBlank() && etRepeatNumberTwo.text.isNullOrBlank() && etRepeatNumberThree.text.isNullOrBlank() && etRepeatNumberFour.text.isNullOrBlank()) {
                    showToast("Error!", false)
                } else if (etRepeatNumberFour.text.isNullOrBlank()) {
                    etRepeatNumberThree.requestFocus()
                } else {
                    enterMainActivity()
                }
            }
        })
    }

    private fun enterMainActivity() {

        val otpNewValue = etNewNumberOne.text.toString() + etNewNumberTwo.text + etNewNumberThree.text.toString() + etNewNumberFour.text.toString()

        val otpRepeatValue = etRepeatNumberOne.text.toString() + etRepeatNumberTwo.text + etRepeatNumberThree.text.toString() + etRepeatNumberFour.text.toString()

        if (otpNewValue == otpRepeatValue) {
            val intent = Intent(context, MainMenuActivity::class.java)
            intent.putExtra("parent", "parent")
            Constants.User.PARENT_PASSWORD = otpRepeatValue
            dialog?.dismiss()
            etRepeatNumberFour.hideKeyboard()
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            showSnackbar("Your passwords don't match!")
        }
    }
}