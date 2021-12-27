package com.clover.domain.clovelist.repository

import com.clover.domain.clovelist.entity.response.CloveData

interface CloveRepository {
    suspend fun getCloveList(): CloveData
}