package com.likchachevskiy.android.mysto

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.likchachevskiy.android.mysto.databinding.FragmentCarListBinding
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.viewmodel.CarViewModel

class CarListFragment : Fragment(R.layout.fragment_car_list) {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    private val carViewModel: CarViewModel by activityViewModels()

    private lateinit var carsAdapter: CarsAdapter

    private var carList = mutableListOf<Car>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        val view = binding.root

        init()

        binding.btnAddCar.setOnClickListener {
            findNavController().navigate(R.id.action_carListFragment_to_addCarFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel.dataLiveData.observe(viewLifecycleOwner) {
            addCar(it)
        }
//        setFragmentResultListener("requestKey") {_, bundle ->
//            val newCar = bundle.getParcelable("extraKey", Car::class.java)
//            if (newCar != null) {
//                carList = carsAdapter.add(newCar)
//            }
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(context)
        val recyclerView: RecyclerView = binding.recyclerViewCars
        recyclerView.layoutManager = layoutManager
        carsAdapter = CarsAdapter(carList)
        recyclerView.adapter = carsAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addCar(car: Car) {
        carList.add(car)
        carsAdapter.notifyDataSetChanged()
    }

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}
