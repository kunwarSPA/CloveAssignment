package com.kotlin.cloverapp.cloveList.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.result.APIResult
import com.clover.domain.clovelist.usecase.Callback
import com.clover.domain.clovelist.usecase.CloveUseCase
import kotlinx.coroutines.launch

class CloveListViewModel  @ViewModelInject constructor(
    private val cloveUseCase: CloveUseCase
) : ViewModel() {

    var cloveuseCaseLiveData = MutableLiveData<APIResult<CloveData>>()


    fun getCloveData() {
        viewModelScope.launch {
            cloveUseCase.getCloveList(cloveuseCaseCallBack)
        }
    }

    private val cloveuseCaseCallBack = object : Callback<CloveData> {
        override fun onSuccess(result: CloveData) {
            cloveuseCaseLiveData.value = APIResult.Success(data =result)
        }

        override fun onError(throwable: Throwable) {
            cloveuseCaseLiveData.value = APIResult.Failure(throwable.toString())
        }
    }

}