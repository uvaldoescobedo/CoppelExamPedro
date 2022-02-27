package com.coppel.exampedro.webService

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebServiceApi {
    private var retrofit: Retrofit? = null
    companion object{
        const val APIKEY="1fab5083d300544a507c7dfb16bf4f08"
        const val HASH = "5dd44cdd953bdd57f52ad9d05cff53b3"
        const val TS = "1"
    }

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .build()
                    chain.proceed(newRequest)
            }.build();
            retrofit = Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com:443/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit
    }

    fun getApi(): API {
        return getClient()!!.create(
            API::class.java
        )
    }
}