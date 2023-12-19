package dev.jay.wouldurather.api

import dev.jay.wouldurather.models.DilemmaModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DilemmaAPI {
    @GET("/question")
    suspend fun getChoiceData(@Query("id") questionId: Int): Response<DilemmaModel>
}