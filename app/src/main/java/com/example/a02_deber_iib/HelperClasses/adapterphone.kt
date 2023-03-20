package com.example.a02_deber_iib.HelperClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_deber_iib.R


class adapterphone(private val phoneLaocations: ArrayList<phonehelper>, private val mOnClickListener: ListItemClickListener) : RecyclerView.Adapter<adapterphone.PhoneViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phonerecyclercard, parent, false)
        return PhoneViewHold(view)
    }

    override fun onBindViewHolder(holder: PhoneViewHold, position: Int) {
        val phonehelper = phoneLaocations[position]
        holder.image.setImageResource(phonehelper.getImage())
        holder.title.text = phonehelper.getTitle()
        holder.relativeLayout.background = phonehelper.getGradient()
    }

    override fun getItemCount(): Int {
        return phoneLaocations.size
    }

    interface ListItemClickListener {
        fun onphoneListClick(clickedItemIndex: Int)
    }

    inner class PhoneViewHold(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var image: ImageView = itemView.findViewById(R.id.phone_image)
        var title: TextView = itemView.findViewById(R.id.phone_title)
        var relativeLayout: RelativeLayout = itemView.findViewById(R.id.background_color)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val clickedPosition = adapterPosition
            mOnClickListener.onphoneListClick(clickedPosition)
        }
    }
}
