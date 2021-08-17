package com.cense.view

import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentClassesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassesFragment : BaseFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}