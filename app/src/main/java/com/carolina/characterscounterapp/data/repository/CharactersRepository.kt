package com.carolina.characterscounterapp.data.repository

import retrofit2.Response

interface CharactersRepository {
    suspend fun fetchEvery10thCharacterRequest(): Response<String>

    suspend fun get10thCharactersTextFromDatabase(): String

    suspend fun insert10thCharactersText(rawText: String)

    suspend fun clear10thCharactersText()

    suspend fun fetchWordCounterRequest(): Response<String>

    suspend fun getWordCounterTextFromDatabase(): String

    suspend fun insertWordCounterText(rawText: String)

    suspend fun clear1WordCounterText()
}
