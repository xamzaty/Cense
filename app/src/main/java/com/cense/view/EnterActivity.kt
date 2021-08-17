package com.cense.view

import android.os.Bundle
import com.cense.base.BaseActivity
import com.cense.databinding.ActivityEnterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterActivity : BaseActivity<ActivityEnterBinding>(ActivityEnterBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}