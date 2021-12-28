package com.clover.data.useCaseImpl

import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.repository.CloveRepository
import com.clover.domain.clovelist.usecase.Callback
import com.clover.domain.clovelist.usecase.CloveUseCase
import javax.inject.Inject

class CloveUseCaseImpl @Inject constructor(private val repository: CloveRepository)  :    CloveUseCase {

    override suspend fun getCloveList( callback: Callback<CloveData>){
        try {
            val result = repository.getCloveList()
            callback.onSuccess(result)
        } catch (e: Exception) {
            callback.onError(e)
        }

    }

}