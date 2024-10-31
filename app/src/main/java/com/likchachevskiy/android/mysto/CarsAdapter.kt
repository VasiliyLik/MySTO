package com.likchachevskiy.android.mysto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.likchachevskiy.android.mysto.databinding.ItemCarBinding
import com.likchachevskiy.android.mysto.domain.entity.Car


class CarsAdapter(
    private val cars: MutableList<Car>,
    private var onCarClickListener: (Car) -> Unit
) :
    RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewHolder(
        ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onCarClickListener
    )

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(cars[position])
    }

    fun update(newList: List<Car>) {
        val diffUtil = DiffUtilCallBack(cars, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        cars.clear()
        cars.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    class CarViewHolder(
        private val binding: ItemCarBinding,
        private val onItemClick: (Car) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(car: Car) {
            binding.apply {
                tvItemCarCarName.text = car.carName
                tvItemCarCarNumber.text = car.plateNumber
                tvItemCarOwnerName.text = car.ownerName
                ivItemCarPhoto.load(car.photo) {
                    crossfade(true)
                    placeholder(R.drawable.ic_no_car_preview)
                }
                itemView.setOnClickListener {
                    onItemClick(car)
                }
            }
        }
    }
}

private class DiffUtilCallBack(
    private val old: List<Car>,
    private val new: List<Car>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}