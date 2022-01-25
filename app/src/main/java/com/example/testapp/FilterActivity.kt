package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.testapp.databinding.ActivityFilterBinding
import com.example.testapp.model.Model
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.assertThreadDoesntHoldLock

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding
    private val filterModel: FilterModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toMapActivity()
    }


    private fun toMapActivity() {
        val intent = Intent(this,MapsActivity::class.java)
        binding.buttonToMap.setOnClickListener {
                filter()
                startActivity(intent)
            }

    }



    private fun filter() {
        val jsonString = JSONParser().loadJSON(this)
        val body = Gson().fromJson(jsonString, Model::class.java)

        binding.checkboxA.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                for (mark in 0..body.pins.size - 1) {
                    if (body.pins[mark].service == body.services[0]) {
                        // this and other Checkbox don't send params in FilterModel
// or it need to do via intent.putExtra(DoubleArray?)
// or intent.putParcelableArrayListExtra()?
                        filterModel.arrayALat.add(body.pins[mark].coordinates.lat)
                        filterModel.arrayALng.add(body.pins[mark].coordinates.lng)
                        filterModel.arrayAService.add(body.pins[mark].service)
                    }
                }
            } else {
                filterModel.arrayALat.clear()
                filterModel.arrayALng.clear()
            }
        }

        binding.checkboxB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                for (mark in 0..body.pins.size - 1) {
                    if (body.pins[mark].service == body.services[1]) {

                        filterModel.arrayBLat.add(body.pins[mark].coordinates.lat)
                        filterModel.arrayBLng.add(body.pins[mark].coordinates.lng)
                        filterModel.arrayBService.add(body.pins[mark].service)

                    }
                }
            } else {
                filterModel.arrayBLat.clear()
                filterModel.arrayBLng.clear()
            }
        }

        binding.checkboxB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                for (mark in 0..body.pins.size - 1) {
                    if (body.pins[mark].service == body.services[2]) {
                        filterModel.arrayCLat.add(body.pins[mark].coordinates.lat)
                        filterModel.arrayCLng.add(body.pins[mark].coordinates.lng)
                        filterModel.arrayCService.add(body.pins[mark].service)

                    }
                }
            } else {
                filterModel.arrayCLat.clear()
                filterModel.arrayCLng.clear()
            }
        }

    }

}