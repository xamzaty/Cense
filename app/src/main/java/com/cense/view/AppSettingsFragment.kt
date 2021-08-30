package com.cense.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentAppSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppSettingsFragment : BaseFragment<FragmentAppSettingsBinding>(FragmentAppSettingsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()
    }

    private fun initSpinner() {

        val language = resources.getStringArray(R.array.choose_language)
        val arrayAdapterLanguage = ArrayAdapter(requireContext(), R.layout.item_dropdown_language, language)
        binding.spinnerLanguage.setAdapter(arrayAdapterLanguage)
    }
}