package com.example.quizapplication.retrofit

import com.example.quizapplication.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsApi
{
    @GET("questionsapi.php")
    suspend fun getQuestions(): Response<QuestionsList>
}