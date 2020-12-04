package com.mytest.myapplication.network

import com.mytest.myapplication.network.model.RepoInfo

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://gtrend.yapie.me/"




private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


interface SimpleApi {

    @GET("repositories?language=kotlin")
    suspend fun getRepository(
        @Query("since") period: String
    ):  List<RepoInfo>

}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


object RetroApi {
    val api : SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java) }
}