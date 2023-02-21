package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=Apple&from=2023-02-16&sortBy=popularity&apiKey=49dbba78d5e54ccbb541a29869b3cd46
//https://newsapi.org/v2/top-headlines?country=in&apiKey=49dbba78d5e54ccbb541a29869b3cd46

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "49dbba78d5e54ccbb541a29869b3cd46"

interface NewsInterface{

    @GET(value="v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String, @Query("page")page: Int): Call<News>
//https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}
object NewsService{
    val newsInstance: NewsInterface
        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsInstance = retrofit.create(NewsInterface::class.java)
        }

    }