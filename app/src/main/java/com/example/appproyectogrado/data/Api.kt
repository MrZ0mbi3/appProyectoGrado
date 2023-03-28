package com.example.appproyectogrado.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Api {
    private val builder: Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl("https://us-central1-irrigation1.cloudfunctions.net/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService {
        @GET("getDataStoreData")
        suspend fun fetchCropData(@Query("amount") amount: Int = 96): CropDataResponse

        //96 is the number of data of a day from the crop
        @GET("makePrediction")
        suspend fun fetchLastPrediction(): PredictionResponse
    }

    fun build(): RemoteService {
        return builder.build().create(RemoteService::class.java)
    }
}