package com.example.myapplication.feature.notification.data.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseService: FirebaseMessagingService() {
    companion object {
        val TAG = FirebaseService::class.java.simpleName
    }
    //SOMETHING
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from)
        if(remoteMessage.data.size > 0){
            Log.d(TAG, "Message payload: " + remoteMessage.data)
            //Something else
        }
        if(remoteMessage.notification != null){
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        }
    }
}