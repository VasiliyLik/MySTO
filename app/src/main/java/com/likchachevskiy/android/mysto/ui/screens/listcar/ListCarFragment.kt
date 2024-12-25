package com.likchachevskiy.android.mysto.ui.screens.listcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.FragmentCarListBinding
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.ui.adapter.CarsAdapter
import com.likchachevskiy.android.mysto.utilits.APP_ACTIVITY
import java.util.Locale

class ListCarFragment : Fragment(R.layout.fragment_car_list) {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CarsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private var tempArrayList = ArrayList<Car>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[ListCarViewModel::class.java]
        viewModel.initDatabase()

        searchView = binding.carSearch
        recyclerView = binding.recyclerViewCars
        adapter = CarsAdapter(tempArrayList)
        recyclerView.adapter = adapter

        viewModel.getAllCars().observe(viewLifecycleOwner) { listCars ->
            adapter.setList(listCars.asReversed())

            if (listCars.isNotEmpty())
                binding.tvCarListInfo.visibility = View.INVISIBLE

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }
            })
        }

        binding.btnAddCar.setOnClickListener {
            findNavController().navigate(R.id.action_carListFragment_to_addCarFragment)
        }
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Car>()
            for (i in tempArrayList) {
                if (i.ownerName.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
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
