package com.carolina.characterscounterapp.data.repository

interface CharactersRepository {
    suspend fun fetchEvery10thCharacterRequest(): String

    suspend fun get10thCharactersTextFromDatabase(): String

    suspend fun insert10thCharactersText(rawText: String)

    suspend fun clear10thCharactersText()

    suspend fun fetchWordCounterRequest(): String

    suspend fun getWordCounterTextFromDatabase(): String

    suspend fun insertWordCounterText(rawText: String)

    suspend fun clear1WordCounterText()
}
