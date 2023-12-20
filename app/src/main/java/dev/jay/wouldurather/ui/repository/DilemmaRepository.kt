package dev.jay.wouldurather.ui.repository

import dev.jay.wouldurather.data.datasource.DilemmaDataSource
import dev.jay.wouldurather.models.DilemmaModel
import dev.jay.wouldurather.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DilemmaRepository @Inject constructor(private val dilemmaDataSource: DilemmaDataSource) {
    suspend fun getChoiceData(questionId: Int): Flow<ResourceState<DilemmaModel>> {
        return flow {
            emit(ResourceState.Loading())

            val response = dilemmaDataSource.getChoiceData(questionId)

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error Fetching News Data"))
            }
        }.catch { e -> emit(ResourceState.Error(e.localizedMessage ?: "Some error in flow")) }
    }
}