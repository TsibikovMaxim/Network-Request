package com.example.test22byte.api

import com.example.test22byte.data.Object
import retrofit2.http.GET

interface Api {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("everything?q=tesla&from=2022-03-06&sortBy=publishedAt&apiKey=5c3589e4530c4be5a4557108d95715b6")
    suspend fun getData(): Object
}