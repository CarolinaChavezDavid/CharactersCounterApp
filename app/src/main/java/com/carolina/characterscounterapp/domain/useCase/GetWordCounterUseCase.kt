package com.carolina.characterscounterapp.domain.useCase

import android.util.Log
import com.carolina.characterscounterapp.data.repository.CharactersRepository
import java.lang.RuntimeException
import javax.inject.Inject

class GetWordCounterUseCase @Inject constructor(private val repository: CharactersRepository) {
        suspend fun execute(): Map<String, Int> {
            var result = mapOf<String, Int>()
            try {
                val rawText = repository.fetchWordCounterRequest()
                if (rawText.isSuccessful)
                    {
                        rawText.body()?.let {
                            result = getWordCounter(it)
                        }
                    } else {
                    result = getWordCounter(repository.get10thCharactersTextFromDatabase())
                }
            } catch (e: Throwable) {
                Log.i("NetworkErrorO", e.localizedMessage ?: "")
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
