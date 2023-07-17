package com.example.wenjiezhou_shengwang_comp304sec002_lab04

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule.Schedule
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.databinding.RecyclerDetailBinding
import java.text.SimpleDateFormat
import java.util.Date

class AdapterDetail(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule, AdapterDetail.FlightDetailViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightDetailViewHolder {
        val viewHolder = FlightDetailViewHolder(
            RecyclerDetailBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FlightDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FlightDetailViewHolder(
        private var binding: RecyclerDetailBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.textViewAirlineNameD.text = schedule.airlineName
            binding.textViewArrivalTimeD.text = SimpleDateFormat("h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000))
            binding.textViewTerminalNumberD.text = schedule.terminalNumber
            binding.textViewStatusD.text = schedule.status
        }
    }
}
