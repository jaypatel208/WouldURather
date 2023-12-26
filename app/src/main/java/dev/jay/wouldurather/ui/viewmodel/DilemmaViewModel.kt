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

    private val _clickCountFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _showVotesPercentage: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showVotesPercentage: StateFlow<Boolean> = _showVotesPercentage

    private val _reloadScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val reloadScreen: StateFlow<Boolean> = _reloadScreen

    init {
        getDilemma(CoreUtility.getRandomQuestion())
    }

    private fun getDilemma(questionId: Int) {
        viewModelScope.launch {
            dilemmaRepository.getChoiceData(questionId).collectLatest { dilemmaResponse ->
                delay(100)
                _dilemma.value = dilemmaResponse
                _reloadScreen.value = false
                onScreenReloaded()
            }
        }
    }

    fun onScreenReloaded() {
        _showVotesPercentage.value = false
    }

    fun onChoiceComponentClick() {
        viewModelScope.launch {
            if (_clickCountFlow.value == 0) {
                _showVotesPercentage.value = true
                _clickCountFlow.value++
            }

            if (_clickCountFlow.value == 1) {
                delay(2300)
                _reloadScreen.value = true
                getDilemma(CoreUtility.getRandomQuestion())
                _clickCountFlow.value = 0
            }
        }
    }
}