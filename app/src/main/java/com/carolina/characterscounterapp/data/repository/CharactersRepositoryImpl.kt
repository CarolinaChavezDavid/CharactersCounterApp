package com.carolina.characterscounterapp.data.repository


import com.carolina.characterscounterapp.data.database.dao.CharactersDao
import com.carolina.characterscounterapp.data.database.entity.CharactersEntity
import com.carolina.characterscounterapp.data.service.CharactersServices
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class CharactersRepositoryImpl
    @Inject
    constructor(
        private val services: CharactersServices,
        private val charactersDao: CharactersDao,
    ) : CharactersRepository {
        override suspend fun fetchEvery10thCharacterRequest(): Response<String> {
            return services.every10thCharacterRequest()
        }

        override suspend fun get10thCharactersTextFromDatabase(): String {
            return charactersDao.getString()
        }

        override suspend fun insert10thCharactersText(rawText: String) {
            charactersDao.insertString(CharactersEntity(content = rawText))
        }

        override suspend fun clear10thCharactersText() {
            charactersDao.deleteAll()
        }

        override suspend fun fetchWordCounterRequest(): Response<String> {
            return services.wordCounterRequest()
        }

        override suspend fun getWordCounterTextFromDatabase(): String {
            return charactersDao.getString()
        }

        override suspend fun insertWordCounterText(rawText: String) {
            charactersDao.insertString(CharactersEntity(id = 2, content = rawText))
        }

        override suspend fun clear1WordCounterText() {
            charactersDao.deleteAll()
        }
    }
