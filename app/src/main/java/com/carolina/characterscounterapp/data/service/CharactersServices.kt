package com.carolina.characterscounterapp.data.service

import retrofit2.http.GET

interface CharactersServices {
    @GET("about/")
    suspend fun every10thCharacterRequest(): String

    @GET("about/")
    suspend fun wordCounterRequest(): String
}
