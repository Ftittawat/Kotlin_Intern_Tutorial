package com.example.tutorial

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class APIPagingAdapter : PagingDataAdapter<Result, APIPagingAdapter.APIViewHolder>(COMPARATOR) {
    class APIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onBindViewHolder(holder: APIViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): APIViewHolder {
        TODO("Not yet implemented")
    }

    companion object {
        private val COMPARATOR = object :DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

}