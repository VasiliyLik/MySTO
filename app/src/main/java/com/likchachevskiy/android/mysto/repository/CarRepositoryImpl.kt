package com.likchachevskiy.android.mysto.repository

import androidx.lifecycle.LiveData
import com.likchachevskiy.android.mysto.data.CarDao
import com.likchachevskiy.android.mysto.domain.entity.Car


class CarRepositoryImpl(private val carDao: CarDao): CarRepository {
    override val allCars: LiveData<List<Car>>
        get() = carDao.getAllCars()

    override suspend fun insertCar(car: Car, onSuccess: () -> Unit) {
        carDao.insertCar(car)
        onSuccess()
    }

    override suspend fun deleteCar(car: Car, onSuccess: () -> Unit) {
        carDao.deleteCar(car)
        onSuccess()
    }

    override suspend fun updateCar(car: Car, onSuccess: () -> Unit) {
        carDao.updateCar(car)
        onSuccess()
    }


}