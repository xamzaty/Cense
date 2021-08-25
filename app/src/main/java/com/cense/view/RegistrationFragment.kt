package com.cense.view

import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentRegistrationBinding
import com.cense.utils.FragmentExtensions.showSnackbar

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

   private fun onClick() {

       with(binding) {

           buttonLogin.setOnClickListener {
               if (editTextLogin.text.isNullOrBlank() || editTextPassword.text.isNullOrBlank() || editTextPhone.text.isNullOrBlank()) {
                   showSnackbar("Fill In All The Fields!")
               } else {
                   showDialog()
               }
           }
       }
    }

    private fun showDialog() {

        SendSmsDialog().show(childFragmentManager, "SendSmsFragment")
    }
}