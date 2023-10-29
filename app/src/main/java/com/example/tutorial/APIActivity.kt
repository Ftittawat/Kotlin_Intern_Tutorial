package com.example.tutorial

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial.databinding.ActivityTvBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

const val BASE_URL = "https://api.themoviedb.org/"
const val USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOTU3YTc4ZmZlYmY2ZGU2MmE0ODhlYjI3MThiYTM5NyIsInN1YiI6IjY0OGZjZjExMmY4ZDA5MDBlMzg2NTUwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Ga6aW-5Fz3X_sANt6uCk86ol67PfKNGMHnRwGXdsurg"
// https://covid19.ddc.moph.go.th/
val list_Tv_pop = ArrayList<Result>()
//val list_History = ArrayList<HistoryData>()
var currentPage: Int = 1

class APIActivity : AppCompatActivity() {
    var list_History = ArrayList<HistoryData>()
    private lateinit var binding: ActivityTvBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TVAdapter
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var progressBar: ProgressBar
    private lateinit var centerProgressBar: ProgressBar

    var startPage: Int = 1
    var loadsuccess: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        //gatMyData()
        binding = ActivityTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nestedScrollView = findViewById(R.id.scrollView)
        progressBar = findViewById(R.id.progressbar)
        centerProgressBar = findViewById(R.id.centerprogressbar)

        //centerProgressBar.setVisibility(View.VISIBLE)
        loadFirstPage()
//        Handler(Looper.myLooper()!!).postDelayed({
//            initrecyclerView()
//        },1000)

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{ nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight) {
                currentPage++
                //progressBar.visibility = View.VISIBLE
                loadNextPage()
            }
        })

        binding.backIcon.setOnClickListener {
            //Log.d("Back","Back Button")
            this.finish()
        }
    }

    private fun gatMyData(): Call<TVPopularData> {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer $USER_TOKEN")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
            .create(ApiInterface::class.java)

        return retrofitBuilder.getData(currentPage)
    }

    /*private fun gatMyData() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer $USER_TOKEN")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(1)
        Log.d("api", "gatMyData")
        Log.d("api", retrofitData.toString())

        retrofitData.enqueue(object : Callback<TVPopularData?> {
            override fun onResponse(call: Call<TVPopularData?>, response: Response<TVPopularData?>) {
                Log.d("api", "onResponse")
                val responseBody = response.body()!!
                //Log.d("api", responseBody.results[0].toString())
                responseBody.results.map { list_Tv_pop.add(it) }
                initrecyclerView()
                //Log.d("api", list_Tv_pop.get(0).toString())
                /*for (i in 0..2) {
                    Log.d("api", responseBody.results[i].toString())
                }*/
                //Log.d("api", responseBody.results.toString())
            }

            override fun onFailure(call: Call<TVPopularData?>, t: Throwable) {
                Log.d("api", "onFailure")
            }
        })
    }*/

    private fun loadFirstPage() {
        progressBar.visibility = View.GONE
        centerProgressBar.visibility = View.VISIBLE
        gatMyData().enqueue(object : Callback<TVPopularData?> {
            override fun onResponse(call: Call<TVPopularData?>, response: Response<TVPopularData?>) {
                centerProgressBar.visibility = View.GONE
                Log.d("api", "onResponse")
                val responseBody = response.body()!!
                //Log.d("api", responseBody.results[0].toString())
                responseBody.results.map { list_Tv_pop.add(it) }
                loadsuccess = true
                initrecyclerView()
            }

            override fun onFailure(call: Call<TVPopularData?>, t: Throwable) {
                Log.d("api", "onFailure")
            }
        })
    }

    private fun loadNextPage() {
        centerProgressBar.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        gatMyData().enqueue(object : Callback<TVPopularData?> {
            override fun onResponse(call: Call<TVPopularData?>, response: Response<TVPopularData?>) {
                progressBar.visibility = View.GONE
                Log.d("api", "onResponse")
                val responseBody = response.body()!!
                //Log.d("api", responseBody.results[0].toString())
                responseBody.results.map { list_Tv_pop.add(it) }
                loadsuccess = true
                initrecyclerView()
            }

            override fun onFailure(call: Call<TVPopularData?>, t: Throwable) {
                Log.d("api", "onFailure")
            }
        })
    }

    private fun initrecyclerView() {
        Log.d("api", "After get Date")
        /*binding.listTv.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this@APIActivity)
            adapter = TVAdapter(list_Tv_pop)
            recyclerView.adapter = adapter
        }*/
        loadData()
        recyclerView = findViewById(R.id.list_tv)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TVAdapter(list_Tv_pop)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : TVAdapter.onItemClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@APIActivity, "Click. $position", Toast.LENGTH_SHORT).show()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val current = LocalDateTime.now().format(formatter)
                list_History.add(HistoryData(list_Tv_pop[position].id.toString(), list_Tv_pop[position].name, current))
                saveData()
                val intent = Intent(this@APIActivity, TVDetailActivity::class.java)
                intent.putExtra("name", list_Tv_pop[position].name)
                intent.putExtra("poster", list_Tv_pop[position].posterPath)
                intent.putExtra("description", list_Tv_pop[position].overview)
                intent.putExtra("rating", list_Tv_pop[position].voteAverage)
                startActivity(intent)
                Log.d("save", list_History.toString())
            }
        })

    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("history", null)
        if (json.isNullOrEmpty())
            list_History = ArrayList()
        else {
            val type: Type = object : TypeToken<ArrayList<HistoryData?>?>() {}.type
            list_History = gson.fromJson(json, type)
            list_History.sortByDescending { it.time }
            while (list_History.size >= 20)
                list_History.removeAt(0)
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        Log.d("loaddata", list_History.toString())
        val json: String = gson.toJson(list_History)
        Log.d("loaddata", json)
        editor.putString("history", json)
        editor.apply()
    }

}
