package com.luisg.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luisg.conf.R
import com.luisg.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    var listConference = ArrayList<Conference>()

    //Cargamos la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule,parent,false))

    //Cantidad de vistas
    override fun getItemCount() = listConference.size

    //Datos a cargar en la vista
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateFormat.format(conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference,position)
        }
    }

    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    //Clases especial para enlazar las referencias de los views
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvConferenceName =itemView.findViewById<TextView>(R.id.txtItemScheduleConferenceName)
        val tvConferenceSpeaker =itemView.findViewById<TextView>(R.id.txtItemScheduleConferenceSpeaker)
        val tvConferenceTag =itemView.findViewById<TextView>(R.id.txtItemScheduleTag)
        val tvConferenceHour =itemView.findViewById<TextView>(R.id.txtItemScheduleHour)
        val tvConferenceAMPM =itemView.findViewById<TextView>(R.id.txtItemScheduleAMPM)
    }

}