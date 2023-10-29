package com.example.tutorial

import com.google.gson.annotations.SerializedName

data class HistoryData(
    @SerializedName("history_id")
    val id: String,
    @SerializedName("history_name")
    val name: String,
    @SerializedName("history_time")
    val time: String
)
