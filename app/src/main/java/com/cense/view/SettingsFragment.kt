package com.cense.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()
    }

    private fun initSpinner() {

        val time = resources.getStringArray(R.array.choose_time)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_time, time)
        binding.spinnerLanguage.setAdapter(arrayAdapter)
    }
}