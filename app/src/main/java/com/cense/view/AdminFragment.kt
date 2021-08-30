package com.cense.view

import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminFragment : BaseFragment<FragmentAdminBinding>(FragmentAdminBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}