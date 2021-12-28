package com.kotlin.employeeaccountapp.cloveList.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clover.domain.clovelist.entity.response.CloveData
import com.clover.domain.clovelist.usecase.CloveUseCase
import com.employee.common.di.addTo
import com.employee.domain.common.usecase.Callback
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.EmployeeDetailUseCase
import com.employee.domain.model.EmployeeData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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