package com.example.testapp

import androidx.lifecycle.ViewModel


class FilterModel: ViewModel() {

  var arrayALat = mutableListOf<Double>()
  var arrayALng = mutableListOf<Double>()
  var arrayAService = mutableListOf<String>()

  var arrayBLat = mutableListOf<Double>()
  var arrayBLng = mutableListOf<Double>()
  var arrayBService = mutableListOf<String>()

  var arrayCLat = mutableListOf<Double>()
  var arrayCLng = mutableListOf<Double>()
  var arrayCService = mutableListOf<String>()


}