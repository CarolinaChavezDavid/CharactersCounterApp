package com.carolina.characterscounterapp.domain.useCase

import com.carolina.characterscounterapp.data.repository.CharactersRepositoryImpl
import javax.inject.Inject

class GetWordCounterUseCase
    @Inject
    constructor(
        private val repository: CharactersRepositoryImpl,
    ) {
        suspend fun execute(): Map<String, Int> {
            val rawText = repository.fetchEvery10thCharacterRequest().subSequence(0, 100)

            return if (!rawText.isNullOrEmpty()) {
                repository.clear1WordCounterText()
                repository.insert10thCharactersText(rawText.toString())
                getWordCounter(rawText.toString())
            } else {
                getWordCounter(repository.getWordCounterTextFromDatabase())
            }
        }

        private fun getWordCounter(rawString: String): Map<String, Int> {
            val wordCount =
                rawString.split("\\s+".toRegex()).groupingBy {
                    it.lowercase()
                }.eachCount()

            return wordCount
        }
    }
