package com.carolina.characterscounterapp.data.di

import com.carolina.characterscounterapp.data.database.dao.CharactersDao
import com.carolina.characterscounterapp.data.repository.CharactersRepository
import com.carolina.characterscounterapp.data.repository.CharactersRepositoryImpl
import com.carolina.characterscounterapp.data.service.CharactersServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppRepositoriesModule {
    @Provides
    fun provideCharactersRepository(
        services: CharactersServices,
        charactersDao: CharactersDao,
    ): CharactersRepository = CharactersRepositoryImpl(services, charactersDao)
}
