package com.likchachevskiy.android.mysto.ui.screens.listcar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.likchachevskiy.android.mysto.data.CarsDatabase
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.data.repository.CarRepositoryImpl
import com.likchachevskiy.android.mysto.utilits.REPOSITORY

class ListCarViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase() {
        val daoCar = CarsDatabase.getInstance(context).getCarDao()
        REPOSITORY = CarRepositoryImpl(daoCar)
    }

    fun getAllCars(): LiveData<List<Car>> {
        return REPOSITORY.allCars
    }

}