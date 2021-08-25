package com.cense.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cense.R
import com.cense.base.BaseFragment
import com.cense.data.Question
import com.cense.databinding.FragmentQuizBinding
import com.cense.utils.Constants

class QuizFragment : BaseFragment<FragmentQuizBinding>(FragmentQuizBinding::inflate) {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mQuestionList = Constants.getQuestions()
        setClassName()
        setQuestion()
        onClick()

    }

    private fun setClassName() {
        val args: QuizFragmentArgs by navArgs()
        val className = args.className

        with(binding) {
            if (className == "english") {
                textViewClassTitle.text = "ENGLISH"
            } else if (className == "math") {
                textViewClassTitle.text = "MATH"
            } else if (className == "science") {
                textViewClassTitle.text = "SCIENCE"
            } else if (className == "random") {
                textViewClassTitle.text = "RANDOM"
            }
        }
    }

    private fun setQuestion() {

        binding.apply {

            val question = mQuestionList!![mCurrentPosition - 1]

            defaultOptionsView()
            if (mCurrentPosition == mQuestionList?.size) {
                btnSubmit.text = "Finish"
            } else {
                btnSubmit.text = "Submit"
            }

            progressBar.progress = mCurrentPosition
            tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

            tvQuestion.text = question.question
            tvOptionOne.text = question.optionOne
            tvOptionTwo.text = question.optionTwo
            tvOptionThree.text = question.optionThree
            tvOptionFour.text = question.optionFour
        }
    }

    private fun defaultOptionsView() {

        binding.apply {
            val options = ArrayList<TextView>()
            options.add(0, tvOptionOne)
            options.add(1, tvOptionTwo)
            options.add(2, tvOptionThree)
            options.add(3, tvOptionFour)

            for (option in options) {
                option.setTextColor(Color.parseColor("#7A8089"))
                option.typeface = Typeface.DEFAULT
                option.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.default_option_border_bg
                    )
                }
            }
        }
    }

    private fun onClick() {

        binding.apply {
                tvOptionOne.setOnClickListener {
                    selectedOptionView(tvOptionOne, 1)
                }
                tvOptionTwo.setOnClickListener {
                    selectedOptionView(tvOptionTwo, 2)
                }
                tvOptionThree.setOnClickListener {
                    selectedOptionView(tvOptionThree, 3)
                }
                tvOptionFour.setOnClickListener {
                    selectedOptionView(tvOptionFour, 4)
                }
                btnSubmit.setOnClickListener {
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++

                        when {
                            mCurrentPosition <= mQuestionList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "You have successfully completed the Quiz", Toast.LENGTH_SHORT
                                ).show()
                                findNavController().popBackStack()
                            }
                        }
                    } else {
                        val question = mQuestionList?.get(mCurrentPosition - 1)
                        if (question!!.correctOption != mSelectedOptionPosition) {
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        }
                        answerView(question.correctOption, R.drawable.correct_option_border_bg)
                        if (mCurrentPosition == mQuestionList!!.size) {
                            btnSubmit.text = "Finish"
                        } else {
                            btnSubmit.text = "Go to next question"
                        }
                        mSelectedOptionPosition = 0
                    }

                }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.selected_option_border_bg
            )
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {

        binding.apply {
            when (answer) {
                1 -> {
                    tvOptionOne.background = context?.let {
                        ContextCompat.getDrawable(
                            it, drawableView
                        )
                    }
                }
                2 -> {
                    tvOptionTwo.background = context?.let {
                        ContextCompat.getDrawable(
                            it, drawableView
                        )
                    }
                }
                3 -> {
                    tvOptionThree.background = context?.let {
                        ContextCompat.getDrawable(
                            it, drawableView
                        )
                    }
                }
                4 -> {
                    tvOptionFour.background = context?.let {
                        ContextCompat.getDrawable(
                            it, drawableView
                        )
                    }
                }
            }
        }
    }
}