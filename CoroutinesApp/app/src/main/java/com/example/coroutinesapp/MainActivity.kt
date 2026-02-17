package com.example.coroutinesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.coroutinesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCounter.setOnClickListener {
            binding.tvCounter.text = (binding.tvCounter.text.toString().toInt() + 1).toString()
        }

        binding.btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadFile()
            }
        }

        // Sequential execution
        CoroutineScope(Dispatchers.IO).launch {
            val one = doSomething1()
            val two = doSomething2()
            val result = one + two
            Log.i("TAGY", "onCreate, Result is: $result")
        }

        // Parallel execution using async
        CoroutineScope(Dispatchers.IO).launch {
            val one = async { doSomething1() }
            val two = async { doSomething2() }
            val result = one.await() + two.await()
            Log.i("TAGY", "onCreate, Result is: $result")
        }

    }

    suspend fun doSomething1(): Int
    {
        delay(3000)
        Log.i("TAGY", "doSomething1: DONE")
        return 11
    }

    suspend fun doSomething2(): Int
    {
        delay(5000)
        Log.i("TAGY", "doSomething2: DONE")
        return 22
    }

    private suspend fun downloadFile()
    {
        for (i in 1..100000)
        {
//            Log.i("TAGY", "downloadFile: $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main)
            {
                binding.tvThread.text = "$i in ${Thread.currentThread().name}"
            }
        }
    }
}