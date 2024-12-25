package com.likchachevskiy.android.mysto.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.ItemCarBinding
import com.likchachevskiy.android.mysto.domain.entity.Car
import com.likchachevskiy.android.mysto.ui.screens.listcar.ListCarFragment


class CarsAdapter(private var cars : MutableList<Car>) :
    RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(cars[position])
        holder.itemView.setOnClickListener {
            ListCarFragment.onClickCar(cars[holder.adapterPosition])
        }
    }

    fun setList(newList: List<Car>) {
        val diffUtil = DiffUtilCallBack(cars, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        cars.clear()
        cars.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(mList: ArrayList<Car>){
        this.cars = mList
        notifyDataSetChanged()
    }

    class CarViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCarBinding.bind(item)

        fun onBind(car: Car) {
            binding.apply {
                tvItemCarName.text = car.carModel
                tvItemCarNumber.text = car.plateNumber
                tvItemOwnerName.text = car.ownerName
                ivItemCarPhoto.load(car.photo) {
                    crossfade(true)
                    placeholder(R.drawable.ic_no_car_preview)
                }
            }
        }
    }
}

private class DiffUtilCallBack(
    private val oldList: List<Car>,
    private val newList: List<Car>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}