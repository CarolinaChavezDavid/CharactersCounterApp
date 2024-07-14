package com.carolina.characterscounterapp.data.di

import com.carolina.characterscounterapp.data.di.ServiceModule.client
import com.carolina.characterscounterapp.data.service.CharactersServices
import com.carolina.characterscounterapp.data.service.HtmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private const val BASE_URL = "https://www.compass.com/"
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val retrofitService: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideCharactersService(): CharactersServices {
        return retrofitService.create(CharactersServices::class.java)
    }
}
