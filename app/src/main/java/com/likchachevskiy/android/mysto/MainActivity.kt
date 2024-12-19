package com.likchachevskiy.android.mysto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likchachevskiy.android.mysto.databinding.ActivityNavHostBinding
import com.likchachevskiy.android.mysto.utilits.APP_ACTIVITY


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavHostBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

    }

}