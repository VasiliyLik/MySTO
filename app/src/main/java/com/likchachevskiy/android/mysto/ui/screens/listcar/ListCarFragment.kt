package com.likchachevskiy.android.mysto.ui.screens.listcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.FragmentCarListBinding
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.ui.adapter.CarsAdapter
import com.likchachevskiy.android.mysto.utilits.BUNDLE
import com.likchachevskiy.android.mysto.utilits.KEY
import kotlinx.coroutines.launch
import java.util.Locale

class ListCarFragment : Fragment(R.layout.fragment_car_list) {

    private val viewModel: ListCarViewModel by viewModels()

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

        observeCars()

        adapter.onItemClick {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE, it)
            setFragmentResult(KEY, bundle)
            findNavController().navigate(R.id.action_carListFragment_to_detailCarFragment)
        }
    }

    private fun observeCars() {
//        val viewModel = ViewModelProvider(this)[ListCarViewModel::class.java]
        viewModel.initDatabase()

        searchView = binding.carSearch
        recyclerView = binding.recyclerViewCars
        adapter = CarsAdapter(tempArrayList)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getAllCars().collect { listCars ->

                    adapter.setList(listCars.asReversed())

                }
            }

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

//    private fun handleOnSuccess(cars: List<Car>) {
//        binding.progressView.visibility = View.GONE
//        adapter.setList(cars)
//        binding.recyclerViewCars.visibility = View.VISIBLE
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
