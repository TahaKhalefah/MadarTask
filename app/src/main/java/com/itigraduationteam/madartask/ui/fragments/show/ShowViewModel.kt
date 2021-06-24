package com.itigraduationteam.madartask.ui.fragments.show

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itigraduationteam.madartask.repository.ShowRepository
import com.itigraduationteam.madartask.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ShowViewModel
@ViewModelInject
constructor(
    val repo: ShowRepository
) : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    var userList = MutableLiveData<List<User>>()


    fun getAllUsers() = repo.getAllUsers()
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
        ?.subscribe({
            if (!it.isNullOrEmpty()) {
                userList.postValue(it)
            } else {
                userList.postValue(listOf())
            }
            it?.forEach {
               // Log.v("User Name", it.mName)
            }
        }, {
        })?.let {
            compositeDisposable.add(it)
        }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}

