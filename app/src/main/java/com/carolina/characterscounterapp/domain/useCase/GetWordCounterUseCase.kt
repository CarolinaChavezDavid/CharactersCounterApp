package com.carolina.characterscounterapp.domain.useCase

import com.carolina.characterscounterapp.data.repository.CharactersRepository
import java.lang.RuntimeException
import javax.inject.Inject

class GetWordCounterUseCase
    @Inject
    constructor(
        private val repository: CharactersRepository,
    ) {
        suspend fun execute(): Map<String, Int> {
            var result = emptyMap<String, Int>()

            try {
                val rawText = repository.fetchWordCounterRequest()
                if (rawText.isSuccess)
                    {
                        rawText.getOrNull()?.let {
                            result = getWordCounter(it)
                        }
                    } else {
                    result = getWordCounter(repository.getWordCounterTextFromDatabase())
                }
            } catch (e: RuntimeException) {
                result = getWordCounter(repository.getWordCounterTextFromDatabase())
            }

            return result
        }

        fun getWordCounter(rawString: String): Map<String, Int> {
            val wordCount =
                rawString.split("\\s+".toRegex()).groupingBy {
                    it.lowercase()
                }.eachCount()

            return wordCount
        }
    }
