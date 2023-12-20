package dev.jay.wouldurather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.wouldurather.models.DilemmaModel
import dev.jay.wouldurather.ui.repository.DilemmaRepository
import dev.jay.wouldurather.utilities.CoreUtility
import dev.jay.wouldurather.utilities.ResourceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DilemmaViewModel @Inject constructor(private val dilemmaRepository: DilemmaRepository) : ViewModel() {
    private val _dilemma: MutableStateFlow<ResourceState<DilemmaModel>> = MutableStateFlow(ResourceState.Loading())
    val dilemma: StateFlow<ResourceState<DilemmaModel>> = _dilemma

    init {
        getDilemma(CoreUtility.getRandomQuestion())
    }

    private fun getDilemma(questionId: Int) {
        viewModelScope.launch {
            dilemmaRepository.getChoiceData(questionId).collectLatest { dilemmaResponse ->
                delay(100)
                _dilemma.value = dilemmaResponse
            }
        }
    }
}