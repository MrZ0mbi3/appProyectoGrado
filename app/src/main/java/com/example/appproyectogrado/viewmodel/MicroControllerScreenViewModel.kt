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
    private var _cropData = MutableStateFlow(listOf<CropData>())
    val cropData = _cropData
    private var _stateFirstMicroController = MutableStateFlow<Boolean>(false)
    val stateFirstMicroController = _stateFirstMicroController
    private val cropDataApi = Api.build()

    init {
        loadCropData()
    }

    private fun loadCropData(amount: Int = 1) {
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount).data.let {
                _cropData.value = it
            }
        }
    }

    fun isMicroControllerActive() {
        val date = cropData.value[0].publishedAt.split("T")
        val cropDataDate = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val currentDate = LocalDate.now()
        _stateFirstMicroController.value = cropDataDate == currentDate
    }
}