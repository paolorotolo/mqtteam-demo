package com.mqtteam.bari2019.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mqtteam.bari2019.R
import com.mqtteam.bari2019.data.Muletto
import com.mqtteam.bari2019.repository.FirebaseRepository
import kotlinx.android.synthetic.main.activity_recycle_view.*
import kotlinx.android.synthetic.main.muletto_item.view.*

class RecycleViewActivity : AppCompatActivity() {

    var currentArea = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        searchButton.setOnClickListener {
            currentArea = spinner.selectedItemId.toInt()
            searchLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            FirebaseRepository.getMuletti(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(data: DataSnapshot) {
                    val list = ArrayList<Muletto>()
                    data.children.forEach {
                        val value = it.getValue(Muletto::class.java)

                        if (value != null) {
                            list.add(value)
                        }

                        recyclerView.layoutManager = LinearLayoutManager(this@RecycleViewActivity)
                        recyclerView.adapter = MyAdapter(list)
                    }

                }
            })

        }
    }

    class MyAdapter(private val dataset: List<Muletto>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.muletto_item, parent, false)

            return MyViewHolder(view)

        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)


        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            val muletto = dataset[position]
            holder.view.nome.text = muletto.name
            holder.view.area.text = muletto.lastPosition
            if (muletto.busy) {
                holder.view.disponibilita.text = "Occupato"
                holder.view.mulettoCard.setBackgroundColor(Color.parseColor("#f44336"))
            } else {
                holder.view.disponibilita.text = "Libero"
                holder.view.mulettoCard.setBackgroundColor(Color.parseColor("#8bc34a"))
                holder.view.rssid.text = muletto.rssi

                holder.view.mulettoCard.setOnClickListener {
                    FirebaseRepository.updateDisponibilityMuletto(muletto.id, true)
                    val intent = Intent(holder.view.context, ClearMuletto::class.java)
                    intent.putExtra("id", muletto.id)
                    holder.view.context.startActivity(intent)
                }
            }
            holder.view.distanza.text = muletto.rssi.toString()


        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataset.size
    }

}
