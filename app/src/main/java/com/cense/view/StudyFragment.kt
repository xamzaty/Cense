package com.cense.view

import android.os.Bundle
import android.view.View
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentStudyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudyFragment : BaseFragment<FragmentStudyBinding>(FragmentStudyBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}