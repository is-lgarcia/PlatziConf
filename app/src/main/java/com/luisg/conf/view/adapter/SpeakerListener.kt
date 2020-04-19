package com.luisg.conf.view.adapter

import com.luisg.conf.model.Speaker

interface SpeakerListener {
    fun onSpeakerListener(speaker: Speaker, position: Int)
}