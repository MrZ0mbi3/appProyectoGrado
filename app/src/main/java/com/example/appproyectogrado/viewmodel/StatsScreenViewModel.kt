package com.example.appproyectogrado.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appproyectogrado.data.Api
import com.example.appproyectogrado.data.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatsScreenViewModel: ViewModel() {
    private var _pokemon = MutableStateFlow(listOf<Pokemon>())
    val pokemon = _pokemon
    private val pokemonApi = Api.build()

    fun loadPokemon(){
        viewModelScope.launch {
            pokemonApi.fetchPokemon(2000).results.let {
                _pokemon.value = it
            }
        }
    }
}