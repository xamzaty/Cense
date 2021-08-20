package com.cense.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentEnterBinding
import com.cense.utils.FragmentExtensions.nextFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterFragment : BaseFragment<FragmentEnterBinding>(FragmentEnterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAction()
    }

    private fun buttonAction() {

        with(binding) {

            buttonParent.setOnClickListener {
                nextFragment(R.id.action_enterFragment_to_loginFragment)
            }
            buttonStartLearning.setOnClickListener {
                startActivity(Intent(activity, MainMenuActivity::class.java))
            }
        }
    }
}