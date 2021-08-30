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
import com.cense.utils.FragmentExtensions.showSnackbar
import com.cense.utils.FragmentExtensions.showToast
import com.cense.utils.ViewExtensions.hideKeyboard
import com.cense.utils.ViewExtensions.showKeyboard
import com.cense.viewmodel.SendSmsViewModel

class SendSmsDialog : DialogFragment() {

    private val viewModel: SendSmsViewModel by viewModels()

    private lateinit var etNumberOne: EditText
    private lateinit var etNumberTwo: EditText
    private lateinit var etNumberThree: EditText
    private lateinit var etNumberFour: EditText
    private lateinit var etNumberFive: EditText
    private lateinit var etNumberSix: EditText

    private lateinit var countdownTextView: TextView
    private lateinit var nextSmsButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner_dialog);
        return inflater.inflate(R.layout.dialog_send_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNumberOne = view.findViewById(R.id.edit_text_number_one)
        etNumberTwo = view.findViewById(R.id.edit_text_number_two)
        etNumberThree = view.findViewById(R.id.edit_text_number_three)
        etNumberFour = view.findViewById(R.id.edit_text_number_four)
        etNumberFive = view.findViewById(R.id.edit_text_number_five)
        etNumberSix = view.findViewById(R.id.edit_text_number_six)

        countdownTextView = view.findViewById(R.id.text_view_next_sms)
        nextSmsButton = view.findViewById(R.id.button_send_sms)

        etNumberOne.requestFocus()
        etNumberOne.showKeyboard()

        otpController()
        onClick()
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
                if(etNumberFour.text.isNullOrBlank()) {
                    etNumberThree.requestFocus()
                } else {
                    etNumberFive.requestFocus()
                }
            }
        })

        etNumberFive.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(etNumberFive.text.isNullOrBlank()) {
                    etNumberFour.requestFocus()
                } else {
                    etNumberSix.requestFocus()
                }
            }
        })

        etNumberSix.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (etNumberOne.text.isNullOrBlank() && etNumberTwo.text.isNullOrBlank() && etNumberThree.text.isNullOrBlank() && etNumberFour.text.isNullOrBlank() && etNumberFive.text.isNullOrBlank() && etNumberSix.text.isNullOrBlank()){
                    showToast("Error!", false)
                } else if(etNumberSix.text.isNullOrBlank()) {
                    etNumberFive.requestFocus()
                } else {
                    sendSmsCode()
                }
            }
        })
    }

    private fun sendSmsCode() {

        val otpValue = etNumberOne.text.toString() + etNumberTwo.text + etNumberThree.text.toString() + etNumberFour.text.toString() + etNumberFive.text.toString() + etNumberSix.text.toString()
        val otpValueInt = otpValue.toInt()

        if(otpValueInt == 111111) {
            startActivity(Intent(context, EnterActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
            etNumberSix.hideKeyboard()
        } else {
            showSnackbar("SMS-code is not correct!")
        }
    }

    private fun onClick() {

        countDownTimer()

        nextSmsButton.setOnClickListener {

            countdownTextView.visibility = View.VISIBLE
            nextSmsButton.visibility = View.GONE

            countDownTimer()
        }
    }

    private fun countDownTimer() {

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownTextView.text = "${activity?.resources?.getString(R.string.send_next_sms_in)} ${millisUntilFinished / 1000} seconds"
            }

            override fun onFinish() {
                countdownTextView.visibility = View.GONE
                nextSmsButton.visibility = View.VISIBLE
            }
        }.start()
    }
}