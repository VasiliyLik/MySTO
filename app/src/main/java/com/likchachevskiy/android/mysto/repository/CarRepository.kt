package com.likchachevskiy.android.mysto.repository

import androidx.lifecycle.LiveData
import com.likchachevskiy.android.mysto.domain.entity.Car

interface CarRepository {

    val allCars: LiveData<List<Car>>

    suspend fun insertCar(car: Car, onSuccess:() -> Unit)

    suspend fun deleteCar(car: Car, onSuccess:() -> Unit)
}