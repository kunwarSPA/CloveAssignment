package com.kotlin.employeeaccountapp.cloveList.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.result.APIResult
import com.clover.domain.clovelist.usecase.Callback
import com.clover.domain.clovelist.usecase.CloveUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class CloveListViewModel  @ViewModelInject constructor(
    private val cloveUseCase: CloveUseCase
) : ViewModel() {

    var userDataLiveData = MutableLiveData<APIResult<CloveData>>()
    var cloveuseCaseLiveData = MutableLiveData<APIResult<CloveData>>()
    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()


    fun getCloveData() {
        viewModelScope.launch {
            cloveUseCase.getCloveList(cloveuseCaseCallBack)
        }
    }

    private val cloveuseCaseCallBack = object : Callback<CloveData> {
        override fun onSuccess(result: CloveData) {
            // employeeDetailUseCase.updateEmployeeDetailInDb()
            cloveuseCaseLiveData.value = APIResult.Success(data =result)
        }

        override fun onError(throwable: Throwable) {
            cloveuseCaseLiveData.value = APIResult.Failure(throwable.toString())
        }
    }

}