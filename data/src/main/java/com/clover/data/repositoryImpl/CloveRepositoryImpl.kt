package com.clover.data.repositoryImpl

import com.clover.data.api.APIClass
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.repository.CloveRepository
import com.employee.data.login.api.Api
import javax.inject.Inject

class CloveRepositoryImpl  @Inject constructor(private val api: Api) : CloveRepository {
    override suspend fun getCloveList(): CloveData {
        return api.getUserDetail()
    }
}