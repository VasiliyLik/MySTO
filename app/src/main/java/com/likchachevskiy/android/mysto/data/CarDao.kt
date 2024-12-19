package com.likchachevskiy.android.mysto.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.likchachevskiy.android.mysto.domain.entity.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>

    @Insert
    fun insertCar(car: Car)

    @Update
    fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)
}