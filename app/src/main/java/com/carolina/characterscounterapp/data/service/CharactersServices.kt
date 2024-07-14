package com.carolina.characterscounterapp.data.service

import retrofit2.http.GET

interface CharactersServices {
    @GET("about/")
    suspend fun every10thCharacterRequest(): Result<String>

    @GET("about/")
    suspend fun wordCounterRequest(): Result<String>
}
