package com.example.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial.databinding.ActivityHistoryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class HistoryActivity : AppCompatActivity() {
    private var list_History = ArrayList<HistoryData>()
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        recyclerView = findViewById(R.id.list_history)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter(list_History)
        recyclerView.adapter = adapter

        binding.backIcon.setOnClickListener {
            //Log.d("Back","Back Button")
            this.finish()
        }
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
        }

        //Log.d("loaddata", list_History[1].toString())
    }

}