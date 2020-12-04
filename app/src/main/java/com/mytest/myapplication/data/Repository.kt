package com.mytest.myapplication.data

import com.mytest.myapplication.network.RetroApi
import com.mytest.myapplication.network.model.RepoInfo


class Repository {
    suspend fun getRepos(period: String): List<RepoInfo> {
        return RetroApi.api.getRepository(period)
    }
}