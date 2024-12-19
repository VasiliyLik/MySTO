package com.likchachevskiy.android.mysto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.likchachevskiy.android.mysto.domain.entity.Car

@Database(entities = [Car::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun getCarDao(): CarDao

    companion object {

        //        @Volatile
        private var database: CarsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CarsDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, CarsDatabase::class.java, "carsdb").build()
                database as CarsDatabase
            } else {
                database as CarsDatabase
            }
        }
    }
}