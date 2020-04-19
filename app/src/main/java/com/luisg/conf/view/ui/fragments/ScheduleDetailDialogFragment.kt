package com.luisg.conf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.luisg.conf.R
import com.luisg.conf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.txtDetailConferenceHour
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*
import java.text.SimpleDateFormat

class ScheduleDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
        toolbarConference.setTitleTextColor(Color.WHITE)
        toolbarConference.setNavigationOnClickListener{
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConference.title = conference.title

        txtScheduleDialogTitle.text = conference.title
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(conference.datetime)
        txtDetailConferenceHour.text = date

        txtDetailConferenceSpeakerName.text = conference.speaker
        txtDetailConferenceTag.text = conference.tag
        txtDetailConferenceDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
