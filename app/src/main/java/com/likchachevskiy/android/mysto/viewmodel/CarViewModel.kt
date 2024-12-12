package com.likchachevskiy.android.mysto.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.likchachevskiy.android.mysto.domain.entity.Car

class CarViewModel : ViewModel() {
    private val data = MutableLiveData<Car>()
    val dataLiveData get() = data

    fun sendData(value: Car) {
        data.value = value
    }
}