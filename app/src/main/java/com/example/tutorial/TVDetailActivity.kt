package com.example.tutorial

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.databinding.ActivityTvdetailBinding
import com.squareup.picasso.Picasso

class TVDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvdetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTvdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.extras!!.getString("name")
        val poster = intent.extras!!.getString("poster")
        val description = intent.extras!!.getString("description")
        val rating = intent.extras!!.getDouble("rating").toString()

        val detail_poster = binding.detailPosterImage
        binding.detailTitle.text = name
        binding.detailDescription.text = description
        binding.rating.text = rating
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + poster).into(detail_poster)

        binding.backIcon.setOnClickListener {
            //Log.d("Back","Back Button")
            this.finish()
        }
    }
}