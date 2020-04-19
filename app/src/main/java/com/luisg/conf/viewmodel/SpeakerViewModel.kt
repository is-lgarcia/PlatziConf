package com.luisg.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luisg.conf.model.Speaker
import com.luisg.conf.network.Callback
import com.luisg.conf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel : ViewModel(){
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        firestoreService.getSpeaker(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}