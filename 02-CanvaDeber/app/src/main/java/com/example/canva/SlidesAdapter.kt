package com.example.canva

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class SlidesAdapter(
    private val slides: List<Int>
    ):RecyclerView.Adapter<SlidesAdapter.ViewHolder>() {
    private val itemView: View
        get() {
            TODO()
        }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(itemView) {
        //ponemos las variables que tenemos en la irtefaz Recycle
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(slides[position])
    }

    override fun getItemCount() = slides.size
}