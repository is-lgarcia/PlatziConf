package com.luisg.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.luisg.conf.R
import com.luisg.conf.model.Speaker
import com.luisg.conf.view.adapter.SpeakerAdapter
import com.luisg.conf.view.adapter.SpeakerListener
import com.luisg.conf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeaker.apply {
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter = speakerAdapter
        }

        observerViewModel()

    }

    fun observerViewModel(){
        viewModel.listSpeaker.observe(viewLifecycleOwner, Observer <List<Speaker>>{ speaker ->
            speakerAdapter.updateData(speaker)
        })

        viewModel.isLoading.observe(viewLifecycleOwner,Observer<Boolean>{
            if (it != null)
                rlBase.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerListener(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog,bundle)
    }

}
