package com.cense.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentEnterBinding
import com.cense.utils.Constants
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
                if (Constants.User.PARENT_PASSWORD.length < 3) {
                    NewParentPasswordDialog().show(childFragmentManager, "NewParentPasswordDialog")
                } else {
                    ParentPasswordDialog().show(childFragmentManager, "ParentPasswordDialogFragment")
                }
            }
            buttonStartLearning.setOnClickListener {
                val intent = Intent(context, MainMenuActivity::class.java)
                intent.putExtra("child", "child")
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
            }
        }
    }
}