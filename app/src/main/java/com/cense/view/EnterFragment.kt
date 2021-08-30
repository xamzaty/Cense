package com.cense.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentEnterBinding
import com.cense.utils.Constants
import com.cense.utils.FragmentExtensions.nextFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterFragment : BaseFragment<FragmentEnterBinding>(FragmentEnterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAction()
        setHasOptionsMenu(true)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val adminBtn = R.id.button_admin
        val appSettingsBtn = R.id.button_settings
        val exitBtn = R.id.button_logout

        when(item.itemId) {
            adminBtn -> nextFragment(R.id.action_enterFragment_to_adminFragment)
            appSettingsBtn -> nextFragment(R.id.action_enterFragment_to_appSettingsFragment)
            exitBtn -> context?.let { alertDialog() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertDialog() {
        context?.let {
            AlertDialog.Builder(it, R.style.Base_ThemeOverlay_AppCompat_Dialog)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_are_you_want_to_exit)
                .setPositiveButton(R.string.alert_ok) {_, _ ->
                    Coroutines.main {
                        val intent = Intent(context, AuthorizationActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }
                }
                .setNegativeButton(R.string.alert_cancel, null).create().show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_enter, menu)
    }
}