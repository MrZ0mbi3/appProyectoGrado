package com.example.appproyectogrado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.CropData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatsScreenViewModel : ViewModel() {
    private var _cropDataD1 = MutableStateFlow(listOf<CropData>())
    val cropDataD1 = _cropDataD1
    private var _cropDataD2 = MutableStateFlow(listOf<CropData>())
    val cropDataD2 = _cropDataD2
    private var _cropDataD3 = MutableStateFlow(listOf<CropData>())
    val cropDataD3 = _cropDataD3
    private val cropDataApi = Api.build()

    init {
        loadCropData()
    }

    private fun loadCropData(amount: Int = 96) {
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "EVA01_1").data.let {
                _cropDataD1.value = it
            }
        }
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "EVA02_1").data.let {
                _cropDataD2.value = it
            }
        }
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "REI_1").data.let {
                _cropDataD3.value = it
            }
        }
    }

    fun makeListPointsHum01(data: List<CropData>): List<Point> {
        val pointsData = mutableListOf<Point>()
        var hum01Avg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        data.forEach { subData ->
            subData.hum01.forEach {
                hum01Avg += it
            }
            actualHour = getHourFromDate(subData.publishedAt)
            hum01Avg /= subData.hum01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = hum01Avg))
                hourUsed.add(actualHour)
            }
            hum01Avg = 0f
        }
        pointsData.sortBy { it.x }
        return pointsData
    }

    fun makeListPointsLight(data: List<CropData>): List<Point> {
        val pointsData = mutableListOf<Point>()
        var luxAvg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        data.forEach { subData ->
            subData.lux01.forEach {
                luxAvg += it
            }
            actualHour = getHourFromDate(subData.publishedAt)
            luxAvg /= subData.lux01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = luxAvg))
                hourUsed.add(actualHour)
            }
            luxAvg = 0f
        }
        pointsData.sortBy { it.x }
        return pointsData
    }

    fun makeListPointsTemp(data: List<CropData>): List<Point> {
        val pointsData = mutableListOf<Point>()
        var tempAvg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        data.forEach { subData ->
            subData.temp01.forEach {
                tempAvg += it
            }
            actualHour = getHourFromDate(subData.publishedAt)
            tempAvg /= subData.temp01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = tempAvg))
                hourUsed.add(actualHour)
            }
            tempAvg = 0f
        }
        pointsData.sortBy { it.x }
        return pointsData
    }

    private fun getHourFromDate(date: String): Float {
        val newDate = date.split("T")
        return (newDate[1][0].toString() + newDate[1][1].toString()).toFloat()
    }
}