package com.likchachevskiy.android.mysto.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.ItemCarBinding
import com.likchachevskiy.android.mysto.domain.entity.Car


class CarsAdapter:
    RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    private var cars = emptyList<Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
//        onCarClickListener
        return CarViewHolder(view)
    }


    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(cars[position])
    }

//    fun update(newList: List<Car>) {
//        val diffUtil = DiffUtilCallBack(cars, newList)
//        val diff = DiffUtil.calculateDiff(diffUtil)
//        cars.clear()
//        cars.addAll(newList)
//        diff.dispatchUpdatesTo(this)
//    }

    class CarViewHolder(item: View): RecyclerView.ViewHolder(item) {
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
//                itemView.setOnClickListener {
//                    onItemClick(car)
//                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Car>) {
        cars = list
        notifyDataSetChanged()
    }
}

//private class DiffUtilCallBack(
//    private val old: List<Car>,
//    private val new: List<Car>
//) : DiffUtil.Callback() {
//    override fun getOldListSize(): Int = old.size
//
//    override fun getNewListSize(): Int = new.size
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return old[oldItemPosition] == new[newItemPosition]
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return old[oldItemPosition] == new[newItemPosition]
//    }
//}