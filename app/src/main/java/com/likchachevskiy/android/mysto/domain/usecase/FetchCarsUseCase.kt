package com.likchachevskiy.android.mysto.domain.usecase

import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class FetchCarsUseCase(private val carRepository: CarRepository) {

    suspend operator fun invoke() : Flow<List<Car>> = carRepository.allCars
}