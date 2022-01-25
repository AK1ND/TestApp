package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Model
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class RecyclerAdapter(private val listModel: Model):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lat:Double? = 00.000000
        var lng: Double? = 00.000000
        lateinit var mMap: GoogleMap
        var mark = LatLng(0.000000, 0.000000)
        var service = ""



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_maps, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.lat = listModel.pins[position].coordinates.lat
        holder.lng = listModel.pins[position].coordinates.lng
        holder.service = listModel.pins[position].service

        holder.mark = LatLng(holder.lat!!, holder.lng!!)
        holder.mMap.addMarker(MarkerOptions().position(holder.mark).title(holder.service))
        holder.mMap.moveCamera(CameraUpdateFactory.newLatLng(holder.mark))
    }


    override fun getItemCount() = listModel.pins.size


}