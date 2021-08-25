package com.cense.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.cense.R
import com.cense.base.BaseActivity
import com.cense.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : BaseActivity<ActivityAuthorizationBinding>(ActivityAuthorizationBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        navBarOptions()

    }

    private fun navBarOptions() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        val navController = findNavController(R.id.nav_host_fragment_container)

        setupActionBarWithNavController(navController)
    }
}