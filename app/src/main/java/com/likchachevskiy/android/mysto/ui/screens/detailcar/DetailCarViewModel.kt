package com.likchachevskiy.android.mysto.ui.screens.detailcar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.utilits.REPOSITORY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCarViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun delete(car: Car, onSuccess: () -> Unit) =
        viewModelScope.launch(dispatcher) {
            REPOSITORY.deleteCar(car) {
                onSuccess()
            }
        }

    fun updateCar(car: Car, onSuccess: () -> Unit) =
        viewModelScope.launch(dispatcher) {
            REPOSITORY.updateCar(car) {
                onSuccess()
            }
        }
}