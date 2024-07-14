package com.carolina.characterscounterapp.domain.useCase

import android.util.Log
import com.carolina.characterscounterapp.data.repository.CharactersRepository
import com.carolina.characterscounterapp.data.repository.CharactersRepositoryImpl
import java.lang.RuntimeException
import javax.inject.Inject

class GetWordCounterUseCase
    @Inject
    constructor(
        private val repository: CharactersRepository,
    ) {
        suspend fun execute(): Map<String, Int> {
            var result = mapOf<String, Int>()

            try{
                val rawText = repository.fetchWordCounterRequest()
                if (rawText.isSuccess){
                    rawText.getOrNull()?.let {
                       result = getWordCounter(it)
                    }
                }else {
                    result = getWordCounter(repository.getWordCounterTextFromDatabase())
                }


            } catch (e: RuntimeException) {
                Log.i("NetworkErrorO", e.message.toString())
                result = getWordCounter(repository.getWordCounterTextFromDatabase())
            }

            return result
        }

        private fun getWordCounter(rawString: String): Map<String, Int> {
            val wordCount =
                rawString.split("\\s+".toRegex()).groupingBy {
                    it.lowercase()
                }.eachCount()

            return wordCount
        }
    }
