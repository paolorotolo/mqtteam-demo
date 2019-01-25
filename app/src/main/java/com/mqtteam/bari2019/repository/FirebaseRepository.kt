package com.mqtteam.bari2019.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mqtteam.bari2019.data.Pacco

class FirebaseRepository {
    companion object {

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