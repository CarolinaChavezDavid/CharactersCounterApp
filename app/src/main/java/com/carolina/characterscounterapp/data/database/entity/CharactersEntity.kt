package com.carolina.characterscounterapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharactersEntity(
    @PrimaryKey val id: Int = 1,
    val content: String,
)
