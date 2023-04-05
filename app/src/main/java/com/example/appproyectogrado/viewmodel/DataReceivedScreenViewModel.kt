package com.example.appproyectogrado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.CropData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DataReceivedScreenViewModel : ViewModel() {
    private var _cropData = MutableStateFlow(listOf<CropData>())
    val cropData = _cropData
    private val cropDataApi = Api.build()

    init {
        loadCropData()
    }

    private fun loadCropData(amount: Int = 96) {
        viewModelScope.launch {
            cropDataApi.fetchCropData(amount).data.let {
                _cropData.value = it
            }
        }
    }
}