package com.meetalikapse.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meetalikapse.myapplication.api.ApiClient
import com.meetalikapse.myapplication.api.GuardianApiService
import com.meetalikapse.myapplication.api.GuardianResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesActivity : AppCompatActivity() {

    private lateinit var articlesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        articlesRecyclerView = findViewById(R.id.recyclerViewArticles)
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchArticles("AI environment")
    }

    private fun fetchArticles(query: String) {
        val apiKey = "" //API KEY HIDDEN
        val call = ApiClient.instance.getArticles(query, apiKey)

        call.enqueue(object : Callback<GuardianResponse> {
            override fun onResponse(call: Call<GuardianResponse>, response: Response<GuardianResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.response?.results
                    if (articles != null) {
                        articlesRecyclerView.adapter = ArticlesAdapter(articles)
                    }
                } else {
                    Toast.makeText(this@ArticlesActivity, "Failed to load articles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GuardianResponse>, t: Throwable) {
                Log.e("API_ERROR", "Error fetching articles", t)
                Toast.makeText(this@ArticlesActivity, "Error fetching articles", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
