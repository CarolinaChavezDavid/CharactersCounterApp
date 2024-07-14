package com.carolina.characterscounterapp.domain.useCase

import android.util.Log
import com.carolina.characterscounterapp.data.repository.CharactersRepository
import java.lang.RuntimeException
import javax.inject.Inject

class GetEvery10thCharacterUseCase
    @Inject
    constructor(
        private val repository: CharactersRepository,
    ) {
        suspend fun execute(): List<String> {
            var result = listOf<String>()

            try {
                val rawText = repository.fetchEvery10thCharacterRequest()
                if (rawText.isSuccessful)
                {
                    repository.clear10thCharactersText()
                    rawText.body()?.let {
                        repository.insert10thCharactersText(it)
                        result = getEvery10thCharacter(it).map { char -> char.toString() }
                    }
                } else {
                    result = getEvery10thCharacter(repository.get10thCharactersTextFromDatabase()).map { it.toString() }
                }
            } catch (e: RuntimeException) {
                Log.i("NetworkError", e.message.toString())
                result = getEvery10thCharacter(repository.get10thCharactersTextFromDatabase()).map { it.toString() }
            }

            return result
        }

        private fun getEvery10thCharacter(rawString: String): List<Char> {
            val charList = rawString.filterIndexed { index, c -> (index + 1) % 10 == 0 }
            return charList.toList()
        }
    }
