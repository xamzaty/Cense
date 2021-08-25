package com.cense.view

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.view.View
import com.cense.BuildConfig
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentAuthorizationBinding
import com.cense.utils.Constants
import com.cense.utils.FragmentExtensions.nextFragment
import com.cense.utils.FragmentExtensions.showSnackbar

class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(BuildConfig.DEBUG) {
            with(binding){
                editTextLogin.setText(Constants.User.LOGIN)
                editTextPassword.setText(Constants.User.PASSWORD)
            }
        }
        onClick()
    }

    private fun onClick() {

        with(binding) {
            buttonLogin.setOnClickListener {
                if (editTextLogin.text.toString() == Constants.User.LOGIN && editTextPassword.text.toString() == Constants.User.PASSWORD){
                    startActivity(Intent(context, EnterActivity::class.java))
                } else {
                    showSnackbar("Incorrect login or password!")
                }
            }
            buttonRegistration.setOnClickListener {
                nextFragment(R.id.action_authorizationFragment_to_registrationFragment)
            }
        }
    }
}