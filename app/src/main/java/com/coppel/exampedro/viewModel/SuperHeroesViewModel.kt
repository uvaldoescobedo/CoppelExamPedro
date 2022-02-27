package com.coppel.exampedro.viewModel

import ResponseListComis
import ResponseListHeroes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coppel.exampedro.Datasource.SuperHeroesDataSource
import com.coppel.exampedro.Repository.SuperHeroesRepository
import kotlinx.coroutines.launch

class SuperHeroesViewModel(repository: SuperHeroesRepository) : ViewModel() {
    lateinit var data: SuperHeroesDataSource
    var responseData = MutableLiveData<ResponseListHeroes>()
    var responseComics = MutableLiveData<ResponseListComis>()
    var responseEvents = MutableLiveData<ResponseListComis>()
    var responseSeries = MutableLiveData<ResponseListComis>()
    var responseStories = MutableLiveData<ResponseListComis>()

    var countlist: Int = 0

    init {
        data = repository.getSuperHeroDataSource()
        data.responseData = responseData
        responseComics = data.responseComics
        responseEvents = data.responseEvents
        responseSeries = data.responseSeries
        responseStories = data.responseStories
    }

    fun getCharaters() {
        viewModelScope.launch { data.getCharacters(countlist) }
    }

    fun getComis(characterId: String) {
        viewModelScope.launch { data.getComis(characterId) }
    }

    fun getEvents(characterId: String) {
        viewModelScope.launch { data.getEvents(characterId) }
    }

    fun getSeries(characterId: String) {
        viewModelScope.launch { data.getSeries(characterId) }
    }

    fun getStories(characterId: String) {
        viewModelScope.launch { data.getStories(characterId) }
    }

    fun getProgress(): MutableLiveData<Boolean> {
        return data.isPogress
    }

}