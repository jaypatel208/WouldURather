package dev.jay.wouldurather.data.datasource

import dev.jay.wouldurather.data.api.DilemmaAPI
import dev.jay.wouldurather.models.DilemmaModel
import retrofit2.Response
import javax.inject.Inject

class DilemmaDataSourceImpl @Inject constructor(private var dilemmaAPI: DilemmaAPI) : DilemmaDataSource {
    override suspend fun getChoiceData(questionId: Int): Response<DilemmaModel> {
        return dilemmaAPI.getChoiceData(questionId)
    }
}