package com.example.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial.databinding.ActivityMainBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setText(R.string.check_header)
        binding.sourceLabel.setText(R.string.source_label)
        binding.sourceText.setText(R.string.source_name)
        binding.destinationLabel.setText(R.string.destination_label)
        binding.destinationText.setText(R.string.destination_name)
        binding.destinationNo.setText(R.string.destination_No)
        binding.totalAmountText.setText(R.string.total_label_1)
        binding.totalBath.setText(R.string.total_label_2)
        binding.warningText.setText(R.string.warning_label)
        binding.buttonconfirm.setText(R.string.confirm_button_label)

        val text = intent.extras!!.getInt("value").toString()

        binding.totalValue.text = text

        binding.buttonconfirm.setOnClickListener {
            Log.d("Back","Back Button")
            this.finish()
        }

        binding.backIcon.setOnClickListener {
            Log.d("Back","Back Button")
            this.finish()
        }
    }

}