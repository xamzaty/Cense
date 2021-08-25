package com.cense.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.cense.R
import com.cense.utils.Constants
import com.cense.utils.FragmentExtensions.showSnackbar
import com.cense.utils.FragmentExtensions.showToast
import com.cense.viewmodel.SendSmsViewModel

class ParentPasswordDialog : DialogFragment() {

    private lateinit var etNumberOne: EditText
    private lateinit var etNumberTwo: EditText
    private lateinit var etNumberThree: EditText
    private lateinit var etNumberFour: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner_dialog);
        return inflater.inflate(R.layout.dialog_parent_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNumberOne = view.findViewById(R.id.edit_text_number_one_password)
        etNumberTwo = view.findViewById(R.id.edit_text_number_two_password)
        etNumberThree = view.findViewById(R.id.edit_text_number_three_password)
        etNumberFour = view.findViewById(R.id.edit_text_number_four_password)

        etNumberOne.requestFocus()

        otpController()
    }

    private fun otpController() {

        etNumberOne.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                etNumberOne.requestFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(etNumberOne.text.isNullOrBlank()) {
                    etNumberOne.requestFocus()
                } else {
                    etNumberTwo.requestFocus()
                }
            }
        })

        etNumberTwo.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(etNumberTwo.text.isNullOrBlank()) {
                    etNumberOne.requestFocus()
                } else {
                    etNumberThree.requestFocus()
                }
            }
        })

        etNumberThree.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(etNumberThree.text.isNullOrBlank()) {
                    etNumberTwo.requestFocus()
                } else {
                    etNumberFour.requestFocus()
                }
            }
        })

        etNumberFour.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (etNumberOne.text.isNullOrBlank() && etNumberTwo.text.isNullOrBlank() && etNumberThree.text.isNullOrBlank() && etNumberFour.text.isNullOrBlank()) {
                    showToast("Error!", false)
                } else if(etNumberFour.text.isNullOrBlank()) {
                    etNumberThree.requestFocus()
                }
                else {
                    enterMainActivity()
                }
            }
        })
    }

    private fun enterMainActivity() {

        val otpValue = etNumberOne.text.toString() + etNumberTwo.text + etNumberThree.text.toString() + etNumberFour.text.toString()

        if(otpValue == Constants.User.PARENT_PASSWORD) {
            val intent = Intent(context, MainMenuActivity::class.java)
            intent.putExtra("parent", "parent")
            dialog?.dismiss()
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            showSnackbar("Your password is not correct!")
        }
    }
}