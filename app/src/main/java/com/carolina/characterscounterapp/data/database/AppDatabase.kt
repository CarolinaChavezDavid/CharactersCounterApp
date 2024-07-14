package com.carolina.characterscounterapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carolina.characterscounterapp.data.database.dao.CharactersDao
import com.carolina.characterscounterapp.data.database.entity.CharactersEntity

@Database(entities = [CharactersEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCharacterText(): CharactersDao
 }
