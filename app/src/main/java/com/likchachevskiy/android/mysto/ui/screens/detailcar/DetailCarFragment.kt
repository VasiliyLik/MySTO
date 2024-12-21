package com.likchachevskiy.android.mysto.ui.screens.detailcar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.FragmentCarDetailBinding
import com.likchachevskiy.android.mysto.domain.entity.Car

class DetailCarFragment : Fragment(R.layout.fragment_car_detail) {

    private var _binding: FragmentCarDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailCarViewModel
    private lateinit var expectCar: Car

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarDetailBinding.inflate(inflater, container, false)

        expectCar = arguments?.getParcelable("car", Car::class.java) as Car

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[DetailCarViewModel::class.java]

        val image = binding.ivAddCarPhoto
        val ownerName = binding.etCarInfoOwnerName
        val producerName = binding.etCarInfoProducer
        val model = binding.etCarInfoModel
        val plateNumber = binding.etCarInfoPlateNumber

        image.setImageResource(expectCar.photo)
        ownerName.text = expectCar.ownerName
        producerName.text = expectCar.carProducer
        model.text = expectCar.carModel
        plateNumber.text = expectCar.plateNumber

        binding.imBtnCarInfoBackStart.setOnClickListener {
            findNavController().navigate(R.id.action_detailCarFragment_to_carListFragment)
        }

        binding.imBtnDelete.setOnClickListener {
            viewModel.delete(expectCar) {}
            findNavController().navigate(R.id.action_detailCarFragment_to_carListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}