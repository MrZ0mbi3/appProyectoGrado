package com.example.appproyectogrado.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Api {
    private val builder: Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService {
        @GET("pokemon")
        suspend fun fetchPokemon(@Query("limit") limit: Int): PokemonResponse
    }
    fun build(): RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}