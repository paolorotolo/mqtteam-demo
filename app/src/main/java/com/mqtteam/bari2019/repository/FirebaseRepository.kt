package com.mqtteam.bari2019.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mqtteam.bari2019.data.Carrello
import com.mqtteam.bari2019.data.Muletto
import com.mqtteam.bari2019.data.Pacco

class FirebaseRepository {
    companion object {
/*
        fun addCarrelloMock() {
            // item1
            // item2
            // item3

            val muletto1 = Muletto().apply {
                id = "item1"
                name = "Muletto 1"
                lastPosition = "1"
                rssi = "100"
            }
            val muletto2 = Muletto().apply {
                id = "item2"
                name = "Muletto 2"
                lastPosition = "2"
                rssi = "100"
            }

            val reference1 = FirebaseDatabase.getInstance().getReference("/muletti").child(muletto1.id)
            val reference2 = FirebaseDatabase.getInstance().getReference("/muletti").child(muletto2.id)
            reference1.setValue(muletto1)
            reference2.setValue(muletto2)


        }*/

        fun updateDisponibilityMuletto(id: String, isBusy: Boolean){
            FirebaseDatabase.getInstance().getReference("/muletti").child(id).child("busy").setValue(isBusy)
        }

        fun updateMuletto(id: String, position: String, rssi: String) {
            FirebaseDatabase.getInstance().getReference("/muletti").child(id).child("lastPosition").setValue(position)
            FirebaseDatabase.getInstance().getReference("/muletti").child(id).child("rssi").setValue(rssi)
        }

        fun getMuletti(listener: ValueEventListener) {
            FirebaseDatabase.getInstance().getReference("/muletti").addValueEventListener(listener)
        }

    }
}