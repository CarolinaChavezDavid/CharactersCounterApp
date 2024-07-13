package com.carolina.characterscounterapp.data.repository

import com.carolina.characterscounterapp.data.database.AppDatabase
import com.carolina.characterscounterapp.data.database.entity.CharactersEntity
import com.carolina.characterscounterapp.data.service.CharactersServices
import javax.inject.Inject

class CharactersRepositoryImpl
    @Inject
    constructor(
        private val services: CharactersServices,
        private val database: AppDatabase,
    ) : CharactersRepository {
        override suspend fun fetchEvery10thCharacterRequest(): String {
            return services.every10thCharacterRequest()
        }

        override suspend fun get10thCharactersTextFromDatabase(): String {
            return database.getEvery10CharacterText().getString()
        }

        override suspend fun insert10thCharactersText(rawText: String) {
            database.getEvery10CharacterText().insertString(CharactersEntity(content = rawText))
        }

        override suspend fun clear10thCharactersText() {
            database.getEvery10CharacterText().deleteAll()
        }

        override suspend fun fetchWordCounterRequest(): String {
            return services.every10thCharacterRequest()
        }

        override suspend fun getWordCounterTextFromDatabase(): String {
            return database.getWordCounterText().getString()
        }

        override suspend fun insertWordCounterText(rawText: String) {
            database.getWordCounterText().insertString(CharactersEntity(id = 2, content = rawText))
        }

        override suspend fun clear1WordCounterText() {
            database.getWordCounterText().deleteAll()
        }
    }
