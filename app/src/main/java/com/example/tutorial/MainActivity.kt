package com.example.tutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.databinding.HomeBinding
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //HomeScreen
    private lateinit var binding: HomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkButton.setText(R.string.check_header)

        binding.apiButton.setOnClickListener {
            intent = Intent(this, APIActivity::class.java)
            startActivity(intent)
        }

        binding.historyButton.setOnClickListener {
            intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.checkButton.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("value", 0)
            startActivity(intent)
        }

        binding.value100.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("value", 100)
            startActivity(intent)
        }

        binding.value500.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("value", 500)
            startActivity(intent)
        }

        binding.value1000.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("value", 1000)
            startActivity(intent)
        }
    }
}