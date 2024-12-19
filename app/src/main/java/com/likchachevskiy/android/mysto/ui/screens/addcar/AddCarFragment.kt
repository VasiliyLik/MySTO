package com.likchachevskiy.android.mysto.ui.screens.addcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.FragmentAddCarBinding
import com.likchachevskiy.android.mysto.domain.entity.Car

class AddCarFragment : Fragment(R.layout.fragment_add_car) {

    private var _binding: FragmentAddCarBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddCarViewModel

    private var indexImage = 0
    private var imageId = R.drawable.ic_no_car_preview

    private val imageIdList = listOf(
        R.drawable.yellow_car,
        R.drawable.supercar,
        R.drawable.lamborghini,
        R.drawable.sport_car,
        R.drawable.police_car
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCarBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(binding) {
        fabEmptyAddCarPhoto.setOnClickListener {
            indexImage++
            if (indexImage > imageIdList.size - 1) indexImage = 0
            imageId = imageIdList[indexImage]
            imageViewInfoCar.setImageResource(imageId)
        }

        viewModel = ViewModelProvider(requireActivity())[AddCarViewModel::class.java]

            imBtnAddCarDone.setOnClickListener {
                val id = 0
                val carName = etAddCarModel.text.toString()
                val carProducer = etAddCarProducer.text.toString()
                val plateNumber = etAddCarPlateNumber.text.toString()
                val photo = imageId
                val ownerName = etAddCarOwnerName.text.toString()
                val car = Car(id, carName, carProducer, plateNumber, photo, ownerName)

                viewModel.insert(car){}

                findNavController().navigate(R.id.action_addCarFragment_to_carListFragment)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}