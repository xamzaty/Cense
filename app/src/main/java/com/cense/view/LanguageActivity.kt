package com.cense.view

import android.content.Intent
import android.os.Bundle
import com.cense.R
import com.cense.base.BaseActivity
import com.cense.databinding.ActivityLanguageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageActivity : BaseActivity<ActivityLanguageBinding>(ActivityLanguageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.languages_select_title)
        onClickListener()
    }

    private fun onClickListener() {

        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        with(binding) {

            buttonLanguageEnglish.setOnClickListener {
                editor.apply {
                    putString("SELECT_ENGLISH", "english")
                }.apply()
                startActivity(Intent(this@LanguageActivity, AuthorizationActivity::class.java))
            }

            buttonLanguageRussian.setOnClickListener {
                editor.apply {
                    putString("SELECT_RUSSIAN", "russian")
                }.apply()
                startActivity(Intent(this@LanguageActivity, AuthorizationActivity::class.java))
            }
        }
    }
}