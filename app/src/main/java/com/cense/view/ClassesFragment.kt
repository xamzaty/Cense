package com.cense.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.databinding.FragmentClassesBinding
import com.cense.utils.Constants
import com.cense.utils.FragmentExtensions.nextFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassesFragment : BaseFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        progress()
    }

    private fun onClick() {

        with(binding) {
            buttonEnglish.setOnClickListener {
                toTheClassFragment("english")
            }
            buttonMaths.setOnClickListener {
                toTheClassFragment("math")
            }
            buttonScience.setOnClickListener {
                toTheClassFragment("science")
            }
            buttonRandom.setOnClickListener {
                toTheClassFragment("random")
            }
        }
    }
    private fun toTheClassFragment(className: String) {
        val action = ClassesFragmentDirections.actionFragmentClassesToQuizFragment(className)
        findNavController().navigate(action)
    }

    private fun progress() {

        with(binding) {
            textViewProgressEnglish.text = "${Constants.ProgressClass.PROGRESS_ENGLISH}%"
            textViewProgressMath.text= "${Constants.ProgressClass.PROGRESS_MATH}%"
            textViewProgressScience.text = "${Constants.ProgressClass.PROGRESS_SCIENCE}%"
            textViewProgressRandom.text = "${Constants.ProgressClass.PROGRESS_RANDOM}%"

            progressBarEnglish.progress = Constants.ProgressClass.PROGRESS_ENGLISH
            progressBarMath.progress = Constants.ProgressClass.PROGRESS_MATH
            progressBarScience.progress = Constants.ProgressClass.PROGRESS_SCIENCE
            progressBarRandom.progress = Constants.ProgressClass.PROGRESS_RANDOM
        }
    }
}