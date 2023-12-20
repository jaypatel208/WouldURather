package dev.jay.wouldurather.data.datasource

import dev.jay.wouldurather.models.DilemmaModel
import retrofit2.Response

interface DilemmaDataSource {
    suspend fun getChoiceData(questionId: Int): Response<DilemmaModel>
}