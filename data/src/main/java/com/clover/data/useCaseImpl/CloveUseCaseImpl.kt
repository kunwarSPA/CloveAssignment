package com.clover.data.useCaseImpl

import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.repository.CloveRepository
import com.clover.domain.clovelist.usecase.CloveUseCase
import javax.inject.Inject

class CloveUseCaseImpl @Inject constructor(private val repository: CloveRepository)  :    CloveUseCase {

    override suspend fun getCloveList(): CloveData {
      return repository.getCloveList()
    }

}