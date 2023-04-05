package com.example.appproyectogrado.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.CropData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatsScreenViewModel : ViewModel() {
    private var _cropData = MutableStateFlow(listOf<CropData>())
    val cropData = _cropData
    private val _chartPointsHum01 = MutableStateFlow(listOf<Point>())
    val chartPointsHum01 = _chartPointsHum01
    private val _chartPointsLux = MutableStateFlow(listOf<Point>())
    val chartPointsLux = _chartPointsLux
    private val _chartPointsTemp = MutableStateFlow(listOf<Point>())
    val chartPointsTemp = _chartPointsTemp
    private val cropDataApi = Api.build()
    private val _firstMicrocontroller = MutableStateFlow<String>("")
    val firstMicrocontroller = _firstMicrocontroller

    init {
        loadCropData()
    }

    private fun loadCropData(amount: Int = 96) {
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount).data.let {
                _cropData.value = it
                makeListPointsHum01()
                makeListPointsLight()
                makeListPointsTemp()
                _firstMicrocontroller.value = cropData.value[0].device
            }
        }
    }

    private fun makeListPointsHum01() {
        val pointsData = mutableListOf<Point>()
        var hum01Avg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        cropData.value.forEach { data ->
            data.hum01.forEach {
                hum01Avg += it
            }
            actualHour = getHourFromDate(data.publishedAt)
            hum01Avg /= data.hum01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = hum01Avg))
                hourUsed.add(actualHour)
            }
            hum01Avg = 0f
        }
        pointsData.sortBy { it.x }
        _chartPointsHum01.value = pointsData
    }

    private fun makeListPointsLight() {
        val pointsData = mutableListOf<Point>()
        var luxAvg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        cropData.value.forEach { data ->
            data.lux01.forEach {
                luxAvg += it
            }
            actualHour = getHourFromDate(data.publishedAt)
            luxAvg /= data.lux01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = luxAvg))
                hourUsed.add(actualHour)
            }
            luxAvg = 0f
        }
        pointsData.sortBy { it.x }
        _chartPointsLux.value = pointsData
    }

    private fun makeListPointsTemp() {
        val pointsData = mutableListOf<Point>()
        var tempAvg = 0f
        val hourUsed = mutableListOf<Float>()
        var actualHour: Float
        cropData.value.forEach { data ->
            data.temp01.forEach {
                tempAvg += it
            }
            actualHour = getHourFromDate(data.publishedAt)
            tempAvg /= data.temp01.size
            if (!hourUsed.contains(actualHour)) {
                pointsData.add(Point(x = actualHour, y = tempAvg))
                hourUsed.add(actualHour)
            }
            tempAvg = 0f
        }
        pointsData.sortBy { it.x }
        _chartPointsTemp.value = pointsData
    }

    private fun getHourFromDate(date: String): Float {
        val newDate = date.split("T")
        Log.d("test", newDate[1][0].toString() + newDate[1][1].toString())
        return (newDate[1][0].toString() + newDate[1][1].toString()).toFloat()
    }
}