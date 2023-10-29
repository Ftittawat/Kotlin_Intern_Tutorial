package com.example.tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(var historylist: ArrayList<HistoryData>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(historyView: View): RecyclerView.ViewHolder(historyView) {
        val id: TextView = historyView.findViewById(R.id.history_detail_id)
        val name: TextView = historyView.findViewById(R.id.history_detail_name)
        val time: TextView = historyView.findViewById(R.id.history_detail_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = historylist[position].id
        holder.name.text = historylist[position].name
        holder.time.text = historylist[position].time
    }

}