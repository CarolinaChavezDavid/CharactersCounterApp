package com.carolina.characterscounterapp.domain.useCase

import com.carolina.characterscounterapp.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

import junit.framework.TestCase.assertEquals
import java.lang.RuntimeException

class GetEvery10thCharacterUseCaseTest {
    private val charactersRepository: CharactersRepository = Mockito.mock(CharactersRepository::class.java)
    private lateinit var useCase: GetEvery10thCharacterUseCase

    val textResult = "Al comienzo de esta devolución de llamada, se inicia el elemento de diseño vectorial animado en la pantalla de presentación."
    val expectedResult = Result.success(textResult)
    val charactersList = listOf("z"," ", "n", "d", "c", "m", "i", "o", "a", "p", "e", "c")

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        useCase = GetEvery10thCharacterUseCase(charactersRepository)
    }

    @Test
    fun `when fetch is successful right list()`() =
        runBlocking {
            Mockito.`when`(charactersRepository.fetchEvery10thCharacterRequest()).thenReturn(expectedResult)
            Mockito.`when`(charactersRepository.get10thCharactersTextFromDatabase()).thenReturn(textResult)
            val result = useCase.execute()
            assertEquals(charactersList, result)
        }

    @Test
    fun `when fetch fails then returns database info`() =
        runBlocking {
            Mockito.`when`(charactersRepository.fetchEvery10thCharacterRequest()).thenReturn(Result.failure(RuntimeException()))
            Mockito.`when`(charactersRepository.get10thCharactersTextFromDatabase()).thenReturn(textResult)
            val result = useCase.execute()
            assertEquals(charactersList, result)
        }
}