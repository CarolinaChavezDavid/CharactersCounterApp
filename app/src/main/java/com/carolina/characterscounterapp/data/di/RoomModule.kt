package com.carolina.characterscounterapp.data.di

import android.content.Context
import androidx.room.Room
import com.carolina.characterscounterapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val CHARACTERS_DATABASE_NAME = "characters_database"

    @Singleton
    @Provides
    fun providesRoom(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, AppDatabase::class.java, CHARACTERS_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCharactersDao(database: AppDatabase) = database.getEvery10CharacterText()
}
