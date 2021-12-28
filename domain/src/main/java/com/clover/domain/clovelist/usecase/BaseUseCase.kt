package com.clover.domain.clovelist.usecase


    interface Callback<in R> {
        fun onSuccess(result: R)
        fun onError(throwable: Throwable)
    }
