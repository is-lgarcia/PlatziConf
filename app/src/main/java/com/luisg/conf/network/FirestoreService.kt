package com.luisg.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.luisg.conf.model.Conference
import com.luisg.conf.model.Speaker

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKER_COLLECTION_NAME = "speakers"


class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        //Nos permitira mantener persistentes nuestros datos aunque este en OFFLINE
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeaker(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKER_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchudule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}