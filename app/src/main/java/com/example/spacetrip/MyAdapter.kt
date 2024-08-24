package com.example.spacetrip

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var rocketArrayList: ArrayList<Rockets>, var context : Activity) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        myListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent, false)
        return MyViewHolder(itemView, myListener)
    }


    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = rocketArrayList[position]
        holder.hTitle.text = currentItem.rocketHeading
        holder.hImage.setImageResource(currentItem.rocketImage)
        holder.hSuccessfullMissions.text = currentItem.rocketSuccessfullMissions
        holder.hSuccessRate.text = currentItem.rocketSuccessRate
        holder.hFirstLaunch.text = currentItem.rocketFirstLaunch
    }

    override fun getItemCount(): Int {
        return rocketArrayList.size
    }


    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val hTitle = itemView.findViewById<TextView>(R.id.headingTitle)
        val hImage = itemView.findViewById<ShapeableImageView>(R.id.headingImage)
        val hSuccessfullMissions = itemView.findViewById<TextView>(R.id.successfullMissions)
        val hSuccessRate = itemView.findViewById<TextView>(R.id.successRate)
        val hFirstLaunch = itemView.findViewById<TextView>(R.id.firstLaunch)


        init {
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
    }

}