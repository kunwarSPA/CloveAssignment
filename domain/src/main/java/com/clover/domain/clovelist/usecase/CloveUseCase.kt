package com.clover.domain.clovelist.usecase

import com.clover.domain.clovelist.entity.response.CloveData
import com.employee.domain.common.usecase.Callback
import com.employee.domain.login.entity.request.EmployeeUpdate

interface CloveUseCase {
    suspend fun getCloveList(callback: Callback<CloveData>)
}