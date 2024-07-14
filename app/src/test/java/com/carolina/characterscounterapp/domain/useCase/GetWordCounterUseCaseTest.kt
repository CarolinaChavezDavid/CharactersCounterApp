package com.carolina.characterscounterapp.domain.useCase

import com.carolina.characterscounterapp.data.repository.CharactersRepository
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.RuntimeException

class GetWordCounterUseCaseTest {
    private val charactersRepository: CharactersRepository = Mockito.mock(CharactersRepository::class.java)
    private lateinit var useCase: GetWordCounterUseCase

    val map =
        mapOf(
            Pair("al", 1),
            Pair("comienzo", 1),
            Pair("de", 4),
            Pair("esta", 1),
            Pair("devolución", 1),
            Pair("llamada,", 1),
            Pair("se", 1),
            Pair("inicia", 1),
            Pair("el", 1),
            Pair("elemento", 1),
            Pair("diseño", 1),
            Pair("vectorial", 1),
            Pair("animado", 1),
            Pair("en", 1),
            Pair("la", 1),
            Pair("pantalla", 1),
            Pair("presentación.", 1),
        )

    val textResult = "Al comienzo de esta devolución de llamada, se inicia el elemento de diseño vectorial animado en la pantalla de presentación."
    val expectedResult = Result.success(textResult)

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        useCase = GetWordCounterUseCase(charactersRepository)
    }

    @Test
    fun `when fetch is successful right list()`() =
        runBlocking {
            Mockito.`when`(charactersRepository.fetchWordCounterRequest()).thenReturn(expectedResult)
            Mockito.`when`(charactersRepository.getWordCounterTextFromDatabase()).thenReturn(textResult)

            val result = useCase.execute()
            TestCase.assertEquals(map, result)
        }

    @Test
    fun `when fetch fails then returns database info`() =
        runBlocking {
            Mockito.`when`(charactersRepository.fetchWordCounterRequest()).thenReturn(
                Result.failure(
                    RuntimeException(),
                ),
            )
            Mockito.`when`(charactersRepository.getWordCounterTextFromDatabase()).thenReturn(textResult)

            val result = useCase.execute()
            TestCase.assertEquals(map, useCase.getWordCounter(textResult))
        }
}
