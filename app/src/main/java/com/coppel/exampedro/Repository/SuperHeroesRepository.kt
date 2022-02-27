package com.coppel.exampedro.Repository

import com.coppel.exampedro.Datasource.SuperHeroesDataSource
import com.coppel.exampedro.webService.API

class SuperHeroesRepository(var api:API) {

    fun getSuperHeroDataSource (): SuperHeroesDataSource {
        return SuperHeroesDataSource(api)
    }
}