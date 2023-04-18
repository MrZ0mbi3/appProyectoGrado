package com.example.appproyectogrado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.CropData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DataReceivedScreenViewModel : ViewModel() {
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
}