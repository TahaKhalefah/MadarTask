package com.itigraduationteam.madartask.ui.fragments.save

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itigraduationteam.madartask.repository.SaveRepository
import com.itigraduationteam.madartask.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SaveViewModel
@ViewModelInject
constructor(
    val repo: SaveRepository
) : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    var userList = MutableLiveData<List<User>>()

    fun insertUser(user: User): Disposable {
        return  repo.insertUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "User added to database ,  id : ${user.mId}")
            }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}

