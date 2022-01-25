package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.testapp.databinding.ActivityMapsBinding
import com.example.testapp.model.Model
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val filterModel: FilterModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        toFilterActivity()

    }

    private fun toFilterActivity() {
        val changeActivity = Intent(this, FilterActivity::class.java)
        binding.sortButton.setOnClickListener {
            startActivity(changeActivity)

        }
    }


    private fun lat(): MutableList<Double> {
        val jsonString = JSONParser().loadJSON(this)
        val body = Gson().fromJson(jsonString, Model::class.java)

        val arrayBodyLat = mutableListOf<Double>()
        for (lat in 0..body.pins.size - 1) {
//            if (body.pins[lat].service == body.services[0]) {
//                arrayBodyLat.add(body.pins[lat].coordinates.lat) //add only "A" services
//            }
            arrayBodyLat.add(body.pins[lat].coordinates.lat)  //add all services
        }
        return arrayBodyLat

    }

    private fun lng(): MutableList<Double> {
        val jsonString = JSONParser().loadJSON(this)

        val body = Gson().fromJson(jsonString, Model::class.java)

        val arrayBodyLng = mutableListOf<Double>()
        for (lng in 0..body.pins.size - 1) {
//            if (body.pins[lng].service == body.services[0]) {
//                arrayBodyLng.add(body.pins[lng].coordinates.lng) //add only "A" services
//            }
            arrayBodyLng.add(body.pins[lng].coordinates.lng) //add all services
        }
        return arrayBodyLng
    }

    private fun service(): MutableList<String> {
        val jsonString = JSONParser().loadJSON(this)

        val body = Gson().fromJson(jsonString, Model::class.java)
        val arrayBodyName = mutableListOf<String>()
        for (lng in 0..body.pins.size - 1) {
//            if (body.pins[lng].service == body.services[0]) {
//                arrayBodyLng.add(body.pins[lng].service) //add only "A" services
//            }
            arrayBodyName.add(body.pins[lng].service) //add all services
        }
        return arrayBodyName
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        try {
        val jsonString = JSONParser().loadJSON(this)

        val body = Gson().fromJson(jsonString, Model::class.java)
        for (mark in 0..body.pins.size-1){ //show all services
//        for (mark in 0..filterModel.arrayALat.size-1){ //from FilterModel
//        for (mark in 0..lat().size - 1) { //show only "A" services
            val marker = LatLng(lat().get(mark), lng().get(mark))
            mMap.addMarker(MarkerOptions().position(marker).title(service().get(mark)))
//                val marker = LatLng(filterModel.arrayALat[mark],
//                        filterModel.arrayALng[mark])
//                mMap.addMarker(MarkerOptions().position(marker)
//                        .title(filterModel.arrayAService[mark]))
        }
        val cameraMove = LatLng(lat().get(0), lng().get(0))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cameraMove))

//        } catch (e: Exception) {
//            Log.d("Error", "Error")
//            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
//        }

    }
}