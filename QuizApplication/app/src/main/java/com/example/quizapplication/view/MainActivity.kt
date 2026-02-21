package com.example.quizapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapplication.databinding.ActivityMainBinding
import com.example.quizapplication.model.Questions
import com.example.quizapplication.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionsList: List<Questions>

    companion object
    {
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // reset the scores
        result = 0
        totalQuestions = 0

        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer{
                if (it.size > 0)
                {
                    questionsList = it
                    binding.tvQuestion.text = questionsList[0].question
                    binding.radio1.text = questionsList[0].option1
                    binding.radio2.text = questionsList[0].option2
                    binding.radio3.text = questionsList[0].option3
                    binding.radio4.text = questionsList[0].option4
                    totalQuestions = questionsList.size

                }
            })
        }

        //btnNext functionality
        var i = 1
        binding.btnNext.setOnClickListener {
            val selectedOption = binding.radioGroup.checkedRadioButtonId
            if (selectedOption != -1)
            {
                val radioButton = findViewById<View>(selectedOption) as RadioButton
                questionsList.let {
                    if (i < it.size)
                    {
                        totalQuestions = it.size
                        if (radioButton.text.toString().equals(it[i-1].correct_option))
                        {
                            result++
                            binding.tvResult.text = "Correct Answer: $result"
                        }

                        binding.tvQuestion.text = "Question ${i+1}: " + it[i].question
                        binding.radio1.text = it[i].option1
                        binding.radio2.text = it[i].option2
                        binding.radio3.text = it[i].option3
                        binding.radio4.text = it[i].option4

                        if (i == it.size.minus(1)) binding.btnNext.text = "Finish"
                        binding.radioGroup.clearCheck()
                        i++
                    } else
                    {
                        if (radioButton.text.toString().equals(it[i-1].correct_option))
                        {
                            result++
                            binding.tvResult.text = "Correct Answer: $result"
                        } else { }
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else
            {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

    }
}