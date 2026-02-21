package com.example.quizapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapplication.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity()
{
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAnswer.text = "Your score is:" + MainActivity.result + "/" + MainActivity.totalQuestions
        binding.btnBack.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}