package com.carolina.characterscounterapp.data.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CharactersServices {
    @GET("about/")
    suspend fun every10thCharacterRequest(): Response<String>

    @GET("about/")
    suspend fun wordCounterRequest(): Response<String>
}
