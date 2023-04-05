package com.example.appproyectogrado.data

import com.google.gson.annotations.SerializedName

data class CropDataResponse(
    @SerializedName("data")
    val data: List<CropData>,
    @SerializedName("message")
    val message: String
)

data class CropData(
    @SerializedName("device")
    val device: String,
    @SerializedName("hum_01")
    val hum01: List<Float>,
    @SerializedName("hum_02")
    val hum02: List<Float>,
    @SerializedName("hygro_01")
    val hygro01: List<Float>,
    @SerializedName("lux_01")
    val lux01: List<Float>,
    @SerializedName("temp_01")
    val temp01: List<Float>,
    @SerializedName("temp_02")
    val temp02: List<Float>,
    @SerializedName("published_at")
    val publishedAt: String,
)

data class PredictionResponse(
    @SerializedName("prediction")
    val prediction: Float,
    @SerializedName("published_at")
    val published_at: String
)