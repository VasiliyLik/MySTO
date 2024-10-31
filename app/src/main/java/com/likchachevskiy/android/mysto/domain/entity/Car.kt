package com.likchachevskiy.android.mysto.domain.entity

data class Car(
    val id: Long,
    val carName: String,
    val carModel: String,
    val plateNumber: String,
    val photo: String,
    val ownerName: String
)
