package com.cense.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cense.R
import com.cense.base.BaseActivity
import com.cense.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        navBarOptions()
    }

    private fun navBarOptions() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        val bottomNavView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_container)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_study, R.id.fragment_classes, R.id.fragment_bonuses, R.id.fragment_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
        bottomNavView.menu.removeItem(R.id.fragment_bonuses)

        val bundle: Bundle? = intent.extras
        val sessionId = bundle?.getString("parent")

        when(sessionId) {
            "parent" -> bottomNavView.menu.removeItem(R.id.fragment_classes)
            else -> bottomNavView.menu.removeItem(R.id.fragment_settings)
        }
        hideBottomMenu()
    }

    private fun hideBottomMenu() {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.fragment_study, R.id.fragment_classes, R.id.fragment_bonuses, R.id.fragment_settings -> showMenu()
                else -> hideMenu()
            }
        }
    }

    private fun showMenu() {
        val bottomMenu = binding.bottomNavigationView
        bottomMenu.visibility = View.VISIBLE
    }

    private fun hideMenu() {
        val bottomMenu = binding.bottomNavigationView
        bottomMenu.visibility = View.GONE
    }

    override fun onBackPressed() {

        AlertDialog.Builder(this, R.style.Base_ThemeOverlay_AppCompat_Dialog)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_are_you_want_to_exit)
            .setPositiveButton(R.string.alert_ok) {_, _ ->
                Coroutines.main {
                    finish()
                }
            }
            .setNegativeButton(R.string.alert_cancel, null).create().show()
    }
}