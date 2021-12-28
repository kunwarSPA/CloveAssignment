package com.clover.domain.clovelist.usecase

import com.clover.domain.clovelist.entity.response.CloveData

interface CloveUseCase {
    suspend fun getCloveList(callback: Callback<CloveData>)
}