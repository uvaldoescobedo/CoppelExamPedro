package com.coppel.exampedro.Datasource

import ResponseListComis
import ResponseListHeroes
import androidx.lifecycle.MutableLiveData
import com.coppel.exampedro.webService.API
import com.coppel.exampedro.webService.WebServiceApi.Companion.APIKEY
import com.coppel.exampedro.webService.WebServiceApi.Companion.HASH
import com.coppel.exampedro.webService.WebServiceApi.Companion.TS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuperHeroesDataSource(var api: API) {
    var responseData = MutableLiveData<ResponseListHeroes>()
    var responseComics = MutableLiveData<ResponseListComis>()
    var responseEvents = MutableLiveData<ResponseListComis>()
    var responseSeries = MutableLiveData<ResponseListComis>()
    var responseStories = MutableLiveData<ResponseListComis>()
    var isPogress = MutableLiveData<Boolean>()


    fun getCharacters(countlist: Int) {
        isPogress.postValue(true)
        api.getCharacters(APIKEY, HASH, TS, countlist.toString())
            .enqueue(object : Callback<ResponseListHeroes> {
                override fun onResponse(
                    call: Call<ResponseListHeroes>,
                    response: Response<ResponseListHeroes>
                ) {
                    isPogress.postValue(false)

                    if (response.isSuccessful)
                        responseData.postValue(response.body())
                    else
                        responseData.postValue(null)
                }

                override fun onFailure(call: Call<ResponseListHeroes>, t: Throwable) {
                    responseData.postValue(null)
                    isPogress.postValue(false)


                }

            })

    }

    fun getComis(characterId: String) {
        isPogress.postValue(true)

        api.getComics(characterId, APIKEY, HASH, TS).enqueue(object : Callback<ResponseListComis> {
            override fun onResponse(
                call: Call<ResponseListComis>,
                response: Response<ResponseListComis>
            ) {
                isPogress.postValue(false)

                if (response.isSuccessful)
                    responseComics.postValue(response.body())
                else
                    responseComics.postValue(null)
            }

            override fun onFailure(call: Call<ResponseListComis>, t: Throwable) {
                responseComics.postValue(null)
                isPogress.postValue(false)


            }

        })

    }

    fun getEvents(characterId: String) {
        isPogress.postValue(true)

        api.getEvents(characterId, APIKEY, HASH, TS).enqueue(object : Callback<ResponseListComis> {
            override fun onResponse(
                call: Call<ResponseListComis>,
                response: Response<ResponseListComis>
            ) {
                isPogress.postValue(false)

                if (response.isSuccessful)
                    responseEvents.postValue(response.body())
                else
                    responseEvents.postValue(null)
            }

            override fun onFailure(call: Call<ResponseListComis>, t: Throwable) {
                responseEvents.postValue(null)
                isPogress.postValue(false)

            }

        })

    }

    fun getSeries(characterId: String) {
        isPogress.postValue(true)

        api.getSeries(characterId, APIKEY, HASH, TS).enqueue(object : Callback<ResponseListComis> {
            override fun onResponse(
                call: Call<ResponseListComis>,
                response: Response<ResponseListComis>
            ) {
                isPogress.postValue(false)

                if (response.isSuccessful)
                    responseSeries.postValue(response.body())
                else
                    responseSeries.postValue(null)
            }

            override fun onFailure(call: Call<ResponseListComis>, t: Throwable) {
                responseSeries.postValue(null)
                isPogress.postValue(false)


            }

        })

    }

    fun getStories(characterId: String) {
        isPogress.postValue(true)
        api.getStories(characterId, APIKEY, HASH, TS).enqueue(object : Callback<ResponseListComis> {
            override fun onResponse(
                call: Call<ResponseListComis>,
                response: Response<ResponseListComis>
            ) {
                isPogress.postValue(false)

                if (response.isSuccessful)
                    responseStories.postValue(response.body())
                else
                    responseStories.postValue(null)
            }

            override fun onFailure(call: Call<ResponseListComis>, t: Throwable) {
                responseStories.postValue(null)
                isPogress.postValue(false)

            }

        })

    }
}