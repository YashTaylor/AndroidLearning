package com.example.quizapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapplication.model.QuestionsList
import com.example.quizapplication.retrofit.QuestionsApi
import com.example.quizapplication.retrofit.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository
{
    var questionsApi: QuestionsApi

    init {
        questionsApi = RetrofitInstance().getRetrofitInstance().create(QuestionsApi::class.java)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getQuestionsFromApi(): LiveData<QuestionsList>
    {
        val data = MutableLiveData<QuestionsList>()
        var questionsList: QuestionsList

        GlobalScope.launch(Dispatchers.IO) {
            val response = questionsApi.getQuestions()
            questionsList = response.body()!!
            data.postValue(questionsList)
        }
        return data
    }
}