package com.niketchoudhary.`in`.androidcodingchallenge.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun PagingRequestHelper.createStatusLiveData(): LiveData<Status> {
    val liveData = MutableLiveData<Status>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(Status.LOADING)
            report.hasError() -> liveData.postValue(Status.ERROR)
            else -> liveData.postValue(Status.SUCCESS)
        }
    }
    return liveData
}