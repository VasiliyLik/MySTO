package com.likchachevskiy.android.mysto.ui.screens.listcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.ui.adapter.CarsAdapter
import com.likchachevskiy.android.mysto.databinding.FragmentCarListBinding
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.utilits.APP_ACTIVITY

class ListCarFragment : Fragment(R.layout.fragment_car_list) {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CarsAdapter

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)

        binding.btnAddCar.setOnClickListener {
            findNavController().navigate(R.id.action_carListFragment_to_addCarFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[ListCarViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.recyclerViewCars
        adapter = CarsAdapter()
        recyclerView.adapter = adapter

        viewModel.getAllCars().observe(viewLifecycleOwner) { listCars ->
            adapter.setList(listCars.asReversed())
        }

        binding.btnAddCar.setOnClickListener {
            findNavController().navigate(R.id.action_carListFragment_to_addCarFragment)
        }
    }

    companion object {

        fun onClickCar(car: Car) {
            val bundle = Bundle()
            bundle.putParcelable("car", car)
            APP_ACTIVITY.findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_carListFragment_to_detailCarFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
