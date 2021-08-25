package com.cense.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentSettingsBinding
import com.cense.utils.FragmentExtensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()
    }

    private fun initSpinner() {

        val time = resources.getStringArray(R.array.choose_time)
        val arrayAdapterTime = ArrayAdapter(requireContext(), R.layout.item_dropdown_time, time)
        binding.spinnerTime.setAdapter(arrayAdapterTime)

        val classes = resources.getStringArray(R.array.choose_class)
        val arrayAdapterClasses = ArrayAdapter(requireContext(), R.layout.item_dropdown_classes, classes)
        binding.spinnerCourse.setAdapter(arrayAdapterClasses)

        val number = resources.getStringArray(R.array.choose_number_of_questions)
        val arrayAdapterNumbers = ArrayAdapter(requireContext(), R.layout.item_dropdown_questions, number)
        binding.spinnerNumberOfQuestions.setAdapter(arrayAdapterNumbers)
    }
}