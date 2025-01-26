package com.meetalikapse.myapplication.api

data class GuardianResponse(
    val response: ResponseData
)

data class ResponseData(
    val results: List<Article>
)

data class Article(
    val id: String,
    val webTitle: String,
    val webUrl: String,
    val fields: Fields
)

data class Fields(
    val thumbnail: String?
)
