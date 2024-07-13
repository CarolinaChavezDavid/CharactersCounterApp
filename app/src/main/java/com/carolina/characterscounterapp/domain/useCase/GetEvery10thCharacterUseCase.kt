package com.carolina.characterscounterapp.domain.useCase

import com.carolina.characterscounterapp.data.repository.CharactersRepositoryImpl
import javax.inject.Inject

class GetEvery10thCharacterUseCase
    @Inject
    constructor(
        private val repository: CharactersRepositoryImpl,
    ) {
        suspend fun execute(): List<String> {
            val rawText = repository.fetchEvery10thCharacterRequest().subSequence(0, 100)

            return if (!rawText.isNullOrEmpty()) {
                repository.clear10thCharactersText()
                repository.insert10thCharactersText(rawText.toString())
                getEvery10thCharacter(rawText.toString()).map { it.toString() }
            } else {
                getEvery10thCharacter(repository.get10thCharactersTextFromDatabase()).map { it.toString() }
            }
        }

        private fun getEvery10thCharacter(rawString: String): List<Char> {
            val charList = rawString.filterIndexed { index, c -> (index + 1) % 10 == 0 }
            return charList.toList()
        }
    }
