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
            val muletto3 = Muletto().apply {
                id = "item3"
                name = "Muletto 3"
                lastPosition = "3"
                rssi = "100"
            }

            val reference1 = FirebaseDatabase.getInstance().getReference("/muletti").child(muletto1.id)
            val reference2 = FirebaseDatabase.getInstance().getReference("/muletti").child(muletto2.id)
            val reference3 = FirebaseDatabase.getInstance().getReference("/muletti").child(muletto3.id)
            reference1.setValue(muletto1)
            reference2.setValue(muletto2)
            reference3.setValue(muletto3)


        }

        fun addPacco(newPacco: Pacco){
            val reference = FirebaseDatabase.getInstance().getReference("/pacco").child(newPacco.registred.toString())
            reference.setValue(newPacco)
        }

        fun getPacchi(): MutableLiveData<List<Pacco?>> {
            val liveData = MutableLiveData<List<Pacco?>>()

            val reference = FirebaseDatabase.getInstance().getReference("/pacco")

            reference.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(data: DataSnapshot) {
                    val list = ArrayList<Pacco?>()

                    data.children.forEach {
                        list.add(it.getValue(Pacco::class.java))
                    }

                    liveData.value = list
                }
            })

            return liveData
        }
    }
}