package com.example.appproyectogrado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.CropData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MicroControllerScreenViewModel : ViewModel() {
    private var _cropDataD1 = MutableStateFlow(listOf<CropData>())
    val cropDataD1 = _cropDataD1
    private var _cropDataD2 = MutableStateFlow(listOf<CropData>())
    val cropDataD2 = _cropDataD2
    private var _cropDataD3 = MutableStateFlow(listOf<CropData>())
    val cropDataD3 = _cropDataD3
    private var _stateFirstMicroController = MutableStateFlow<Boolean>(false)
    val stateFirstMicroController = _stateFirstMicroController
    private var _stateSecondMicroController = MutableStateFlow<Boolean>(false)
    val stateSecondMicroController = _stateSecondMicroController
    private var _stateThirdMicroController = MutableStateFlow<Boolean>(false)
    val stateThirdMicroController = _stateThirdMicroController
    private val cropDataApi = Api.build()

    init {
        loadCropData()
    }

    private fun loadCropData(amount: Int = 1) {
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "SYS01_1").data.let {
                _cropDataD1.value = it
            }
        }
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "SYS02_1").data.let {
                _cropDataD2.value = it
            }
        }
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount, "wSYS_1").data.let {
                _cropDataD3.value = it
            }
        }
    }

    fun isMicroControllerActive() {
        val currentDate = LocalDate.now()
        var date: List<String>
        var cropDataDate: LocalDate
        if (cropDataD1.value.isNotEmpty()) {
            date = cropDataD1.value[0].publishedAt.split("T")
            cropDataDate = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            _stateFirstMicroController.value = cropDataDate == currentDate
        }

        if (cropDataD2.value.isNotEmpty()) {
            date = cropDataD2.value[0].publishedAt.split("T")
            cropDataDate = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            _stateSecondMicroController.value = cropDataDate == currentDate
        }
        if (cropDataD3.value.isNotEmpty()) {
            date = cropDataD3.value[0].publishedAt.split("T")
            cropDataDate = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            _stateThirdMicroController.value = cropDataDate == currentDate
        }
    }

    fun makeAverage(data: List<Float>): Float {
        var average = 0f
        data.forEach {
            average += it
        }
        return average / data.size
    }
}