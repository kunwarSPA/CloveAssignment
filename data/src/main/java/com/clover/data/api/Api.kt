package com.clover.data.api

import com.clover.domain.clovelist.entity.response.CloveData
import retrofit2.http.GET

interface Api {

    @GET("api/character")
    suspend fun getUserDetail(): CloveData

 
}