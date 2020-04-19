package com.luisg.conf.view.adapter

import com.luisg.conf.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}