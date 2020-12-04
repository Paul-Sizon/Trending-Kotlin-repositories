package com.mytest.myapplication.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mytest.myapplication.data.Repository
import com.mytest.myapplication.network.model.RepoInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel(application: Application) : AndroidViewModel(application) {

    val repoLive: MutableLiveData<List<RepoInfo>> = MutableLiveData()
    private val repository: Repository = Repository()

    fun getTheRepo(period: String) = viewModelScope.launch(Dispatchers.Default) {
        try {
            val response = repository.getRepos(period)
            repoLive.postValue(response)
        } catch (e: Exception) {
            repoLive.postValue(null)
            Log.i("myerror", e.message.toString())

        }
    }


}