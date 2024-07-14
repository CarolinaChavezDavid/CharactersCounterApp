package com.carolina.characterscounterapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carolina.characterscounterapp.data.database.entity.CharactersEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertString(stringEntity: CharactersEntity)

    @Query("SELECT content FROM characters_table WHERE id = 1")
    suspend fun getString(): String

    @Query("DELETE FROM characters_table")
    suspend fun deleteAll()
}