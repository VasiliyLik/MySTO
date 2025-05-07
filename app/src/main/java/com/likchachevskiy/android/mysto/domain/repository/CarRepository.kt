package com.likchachevskiy.android.mysto.domain.repository

import com.likchachevskiy.android.mysto.domain.entity.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    val allCars: Flow<List<Car>>

    suspend fun insertCar(car: Car, onSuccess:() -> Unit)

    suspend fun deleteCar(car: Car, onSuccess:() -> Unit)

    suspend fun updateCar(car: Car, onSuccess:() -> Unit)
}