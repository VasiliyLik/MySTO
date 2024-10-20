package com.likchachevskiy.android.homework6sto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transaction1 = supportFragmentManager.beginTransaction()
        transaction1.add(R.id.uiContainer, CarListFragment(), "CarListFragment")
        transaction1.commit()
    }

}