package com.meetalikapse.myapplication.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianApiService {
    @GET("search")
    fun getArticles(
        @Query("q") query: String,          // Keyword for searching articles
        @Query("api-key") apiKey: String    // Your Guardian API key
    ): Call<GuardianResponse>
}
