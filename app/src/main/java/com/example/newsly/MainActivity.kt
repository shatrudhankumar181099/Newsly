package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1
   // val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
       // Log.d(TAG,"First Visible Item - ${layoutManager.getFirstVisibleItemPosition()}")
        getNews()
    }

    private fun getNews(){
        val news  = NewsService.newsInstance.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable){
                Log.d("DragonBlood", "Error in Fetching News", t)
            }
            override fun onResponse(call: Call<News>, response: Response<News>){
                val news = response.body()
                if (news!= null){
                    Log.d("DragonBlood",news.toString())
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}


