package com.example.appproyectogrado.data

import com.google.gson.annotations.SerializedName

data class CropDataResponse(
    @SerializedName("data")
    val data: List<CropData>,
    @SerializedName("message")
    val message: String
)

data class CropData(
    val device: String,
    val hum01: List<Float>,
    val hum02: List<Float>,
    val hygro01: List<Float>,
    val lux01: List<Float>,
    val temp01: List<Float>,
    val temp02: List<Float>,
    val published_at: String,
)

data class PredictionResponse(
    @SerializedName("prediction")
    val prediction: Float,
    @SerializedName("published_at")
    val published_at: String
)