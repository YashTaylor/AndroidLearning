package com.example.livedata

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel()
{
    //ViewModel
    /*private var counter = 0

    fun increaseCounter()
    {
        counter++
    }

    fun getCounter(): Int
    {
        return counter
    }*/

    //LiveData
    var counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }

    fun increaseCounter(view: View)
    {
        counter.value = (counter.value)?.plus(1)
    }
}