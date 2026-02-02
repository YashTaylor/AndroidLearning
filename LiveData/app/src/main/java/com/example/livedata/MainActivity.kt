package com.example.livedata

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var counterViewModel: CounterViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        counterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        binding.textView2.text = counterViewModel.getCounter().toString()

        binding.button.setOnClickListener {
            counterViewModel.increaseCounter()
            binding.textView2.text = counterViewModel.getCounter().toString()
        }
    }
}