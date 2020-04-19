package com.luisg.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luisg.conf.R
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luisg.conf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    val listSpeaker = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun getItemCount() = listSpeaker.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeaker[position] as Speaker

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWorkplace.text = speaker.workspace

        //Transformar una imagen que proviene de un url
        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivImageSpeaker)

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerListener(speaker,position)}
    }

    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivImageSpeaker = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.txtItemSpeakerName)
        val tvSpeakerWorkplace = itemView.findViewById<TextView>(R.id.txtItemSpeakerWorplace)
    }
}