package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.observer

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.repo.ShaadiRepository
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AbsentLiveData
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AppExecutors
import javax.inject.Inject

class ShaadiUserDataViewModel @Inject constructor(
    var repo: ShaadiRepository,
    var executors: AppExecutors
) : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    var position: Int = 0

    override
    fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    var shouldFetch: Boolean = true

    var pageCall = MutableLiveData<String>()


    val repoResult = Transformations.map(pageCall) {
        repo.fetchAllUserList()
    }

    val posts = Transformations.switchMap(repoResult) {
        when (repoResult.value) {
            null -> AbsentLiveData.create()
            else -> it.pagedList
        }
    }


    val networkState = Transformations.switchMap(repoResult) { it.networkState }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }

    fun init() {
        if (pageCall.value == "1") return

        pageCall.value = "1"
    }

    var flag: Boolean = true

    fun deleteList() {
        if (flag) {
            repo.deleteList()
            flag = false
        }
    }

    fun updateMatchStatus(id: String, selectionStatus: String, position: Int) {
        repo.updateMatchStatus(id = id, selectionStatus = selectionStatus)
        this.position = position
        notifyChange()
    }
}