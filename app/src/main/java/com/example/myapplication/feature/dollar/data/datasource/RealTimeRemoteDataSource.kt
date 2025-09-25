package com.example.myapplication.feature.dollar.data.datasource

import com.example.myapplication.feature.dollar.domain.model.DollarModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RealTimeRemoteDataSource{
    suspend fun getDollarUpdates(): Flow<DollarModel> = callbackFlow {
        val callback = object : ValueEventListener{
            override fun onCancelled(pO: DatabaseError) {
                TODO("Not yet implemented")
                //close(pO.toException())
            }

            override fun onDataChange(pO: DataSnapshot) {

                val value = pO.getValue(DollarModel::class.java)
                if(value!=null){
                    trySend(value)
                }
            }
        }
        val database = Firebase.database
        val myRef = database.getReference("dollar")
        myRef.addValueEventListener(callback)
        awaitClose {
            myRef.removeEventListener(callback)
        }
    }
}

