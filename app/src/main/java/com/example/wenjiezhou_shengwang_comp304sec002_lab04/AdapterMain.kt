package com.example.wenjiezhou_shengwang_comp304sec002_lab04

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule.Schedule
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.databinding.RecyclerMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class AdapterMain(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule, AdapterMain.FlightMainViewHolder>(DiffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightMainViewHolder {
        val viewHolder = FlightMainViewHolder(
            RecyclerMainBinding.inflate(
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

    override fun onBindViewHolder(holder: FlightMainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FlightMainViewHolder(
        private var binding: RecyclerMainBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.textViewAirlineNameM.text = schedule.airlineName
            binding.textViewArrivalTimeM.text = SimpleDateFormat("h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000))
            binding.textViewTerminalNumberM.text = schedule.terminalNumber
        }
    }
}


