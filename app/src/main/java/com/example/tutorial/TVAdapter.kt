package com.example.tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TVAdapter(val itemlist: ArrayList<Result>):
    RecyclerView.Adapter<TVAdapter.ViewHolder>() {

    private lateinit var mLIstener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mLIstener = listener
    }

    class ViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val poster_image: ImageView = itemView.findViewById(R.id.poster_image)
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val description_overview: TextView = itemView.findViewById(R.id.description_overview)
        val card: CardView = itemView.findViewById(R.id.card)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view, mLIstener)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + itemlist[position].posterPath).into(holder.poster_image)
        //holder.poster_image.setImageURI(Uri.parse("https://image.tmdb.org/t/p/w100" + itemlist[position].posterPath))
        holder.title.text = itemlist[position].name
        holder.description_overview.text = itemlist[position].overview
        holder.description.text = itemlist[position].voteAverage.toString()

//        holder.itemView.setOnClickListener {
//
//        }
    }
}
