package com.likchachevskiy.android.mysto.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.likchachevskiy.android.mysto.R
import com.likchachevskiy.android.mysto.databinding.ItemCarBinding
import com.likchachevskiy.android.mysto.domain.entity.Car


class CarsAdapter(private var cars: MutableList<Car>) :
    RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    private var onClickListener: ((car: Car) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewHolder(
        ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        , onClickListener
    )

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.onBind(cars[position])
    }

    fun setList(newList: List<Car>) {
        val diffUtil = DiffUtilCallBack(cars, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        cars.clear()
        cars.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(mList: ArrayList<Car>) {
        this.cars = mList
        notifyDataSetChanged()
    }

    class CarViewHolder(
        private val binding: ItemCarBinding,
        private val onItemClick: ((car: Car) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(car: Car) {
            binding.apply {
                tvItemCarName.text = car.carModel
                tvItemCarNumber.text = car.plateNumber
                tvItemOwnerName.text = car.ownerName
                ivItemCarPhoto.load(car.photo) {
                    crossfade(true)
                    placeholder(R.drawable.ic_no_car_preview)
                }
                viewItem.setOnClickListener {
                    onItemClick?.invoke(car)
                    Toast.makeText(itemView.context, "Click ${car.ownerName}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onItemClick(onItemClick: (car: Car) -> Unit) {
        this.onClickListener = onItemClick
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