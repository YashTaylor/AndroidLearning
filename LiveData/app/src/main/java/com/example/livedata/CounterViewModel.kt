package com.example.livedata

import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel()
{
    private var counter = 0

    fun increaseCounter()
    {
        counter++
    }

    fun getCounter(): Int
    {
        return counter
    }
}