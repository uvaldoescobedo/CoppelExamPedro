package com.coppel.exampedro.webService

import ResponseListComis
import ResponseListHeroes
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("public/characters?limit=18")
    fun getCharacters(@Query("apikey")apikey:String,@Query("hash")hash:String,@Query("ts")ts:String,@Query("offset")offset:String): Call<ResponseListHeroes>

    @GET("public/characters/{idcharacter}/comics?limit=100")
    fun getComics(@Path("idcharacter")  idcharacter:String, @Query("apikey")apikey:String, @Query("hash")hash:String, @Query("ts")ts:String): Call<ResponseListComis>

    @GET("public/characters/{idcharacter}/events?limit=100")
    fun getEvents( @Path("idcharacter")  idcharacter:String,@Query("apikey")apikey:String, @Query("hash")hash:String, @Query("ts")ts:String): Call<ResponseListComis>

    @GET("public/characters/{idcharacter}/series?limit=100")
    fun getSeries( @Path("idcharacter")  idcharacter:String,@Query("apikey")apikey:String, @Query("hash")hash:String, @Query("ts")ts:String): Call<ResponseListComis>

    @GET("public/characters/{idcharacter}/stories?limit=100")
    fun getStories( @Path("idcharacter")  idcharacter:String,@Query("apikey")apikey:String, @Query("hash")hash:String, @Query("ts")ts:String): Call<ResponseListComis>

}