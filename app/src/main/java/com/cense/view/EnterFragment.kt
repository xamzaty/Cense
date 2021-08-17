package com.cense.view

import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentEnterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterFragment : BaseFragment<FragmentEnterBinding>(FragmentEnterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}