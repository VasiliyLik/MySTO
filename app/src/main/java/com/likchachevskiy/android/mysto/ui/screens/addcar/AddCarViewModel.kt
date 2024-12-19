package com.likchachevskiy.android.mysto.ui.screens.addcar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.utilits.REPOSITORY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCarViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun insert(car: Car, onSuccess: () -> Unit) =
        viewModelScope.launch(dispatcher) {
            REPOSITORY.insertCar(car) {
                onSuccess()
            }
        }
}