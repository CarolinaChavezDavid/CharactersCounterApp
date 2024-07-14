package com.carolina.characterscounterapp.ui.viewModel

import com.carolina.characterscounterapp.data.repository.CharactersRepository
import com.carolina.characterscounterapp.domain.useCase.GetEvery10thCharacterUseCase
import com.carolina.characterscounterapp.domain.useCase.GetWordCounterUseCase
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CharactersViewModelTest {
    private val charactersRepository: CharactersRepository = Mockito.mock(CharactersRepository::class.java)
    private val getEvery10thCharacterUseCase = GetEvery10thCharacterUseCase(charactersRepository)
    private val getWordCounterUseCase = GetWordCounterUseCase(charactersRepository)
    private lateinit var charactersViewModel: CharactersViewModel

    val charactersList = listOf("d", "f", "y", "b", "", "1")
    val map = mapOf(Pair("d", 1))
    val textResult = "Al comienzo de esta devolución de llamada, se inicia el elemento de diseño vectorial animado en la pantalla de presentación."
    val expectedResult = Result.success("Mocked response")
    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        charactersViewModel = CharactersViewModel(getEvery10thCharacterUseCase, getWordCounterUseCase)
    }

    @Test
    fun `when getData call is right then uiState is success`() =
        runBlocking {
            Mockito.`when`(charactersRepository.fetchEvery10thCharacterRequest()).thenReturn(expectedResult)
            Mockito.`when`(charactersRepository.fetchWordCounterRequest()).thenReturn(expectedResult)
            Mockito.`when`(charactersRepository.getWordCounterTextFromDatabase()).thenReturn(textResult)
            Mockito.`when`(charactersRepository.get10thCharactersTextFromDatabase()).thenReturn(textResult)

            charactersViewModel.getData()
            assertEquals(UiState.Success, charactersViewModel.uiState.value)
        }

}