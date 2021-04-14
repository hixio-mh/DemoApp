package io.goooler.demoapp.main.repository

import io.goooler.demoapp.common.http.RetrofitHelper
import io.goooler.demoapp.main.api.MainApi

object MainRepository {

    private const val USER = "goooler"

    private val api by lazy {
        RetrofitHelper.createApiService(MainApi::class.java)
    }

    suspend fun getRepoListCr(user: String = USER) = api.getRepoListCr(user)

    fun getRepoListRx(user: String = USER) = api.getRepoListRx(user)
}