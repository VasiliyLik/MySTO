package com.likchachevskiy.android.mysto.ui.screens.listcar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.likchachevskiy.android.mysto.data.CarsDatabase
import com.likchachevskiy.android.mysto.data.repository.CarRepositoryImpl
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.utilits.REPOSITORY
import kotlinx.coroutines.flow.Flow

class ListCarViewModel(
    application: Application,
//    private val fetchCarsUseCase: FetchCarsUseCase
) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase() {
        val daoCar = CarsDatabase.getInstance(context).getCarDao()
        REPOSITORY = CarRepositoryImpl(daoCar)
    }

    fun getAllCars(): Flow<List<Car>> {
        return REPOSITORY.allCars
    }
}