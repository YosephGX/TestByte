package com.theyoseph.byteswtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val postModel: MutableList<ResponseAPIElement>): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return  PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postModel.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }
}

class PostViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
    private val cardTitle = itemView.findViewById<TextView>(R.id.cardTitle)
    private val cardSubTitle = itemView.findViewById<TextView>(R.id.cardSubTitle)

    fun bindView(postModel: ResponseAPIElement){
        cardTitle.text = "Nombre: ${postModel.name}"
        cardSubTitle.text = "Tipo: ${postModel.type}"
    }
}