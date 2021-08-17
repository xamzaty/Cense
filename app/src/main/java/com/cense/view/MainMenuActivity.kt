package com.cense.view

import android.os.Bundle
import com.cense.base.BaseActivity
import com.cense.databinding.ActivityMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}