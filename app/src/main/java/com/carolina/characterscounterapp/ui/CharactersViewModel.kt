package com.carolina.characterscounterapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolina.characterscounterapp.domain.useCase.GetEvery10thCharacterUseCase
import com.carolina.characterscounterapp.domain.useCase.GetWordCounterUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel
    @Inject
    constructor(
        private val getEvery10thCharacterUseCase: GetEvery10thCharacterUseCase,
        private val getWordCounterUseCase: GetWordCounterUseCase,
    ) : ViewModel() {
        private var _every10thCharacter = MutableStateFlow(emptyList<String>())
        val every10thCharacter: StateFlow<List<String>> = _every10thCharacter

        private var _wordCounter = MutableStateFlow(emptyMap<String, Int>())
        val wordCounter: StateFlow<Map<String, Int>> = _wordCounter

        private var _uiState = MutableStateFlow<UiState>(UiState.Loading)
        val uiState: StateFlow<UiState> = _uiState

        fun getData() {
            viewModelScope.launch {
                val wordCounterDeferred =
                    async {
                        getWordCounterUseCase.execute()
                    }
                val every10thCharacterDeferred =
                    async {
                        getEvery10thCharacterUseCase.execute()
                    }
                val result = awaitAll(wordCounterDeferred, every10thCharacterDeferred)
                _every10thCharacter.value = result[1] as List<String>
                _wordCounter.value = result[0] as Map<String, Int>

                _uiState.value = UiState.Success
            }
        }
    }

sealed interface UiState {
    data object Success : UiState
    data object Loading : UiState
}
