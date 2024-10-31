package com.likchachevskiy.android.mysto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.likchachevskiy.android.mysto.databinding.FragmentCarListBinding

class CarListFragment : Fragment(R.layout.fragment_car_list) {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    private lateinit var carsAdapter : CarsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerViewCars.adapter = carsAdapter

        binding.btnAddCar.setOnClickListener {


        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
