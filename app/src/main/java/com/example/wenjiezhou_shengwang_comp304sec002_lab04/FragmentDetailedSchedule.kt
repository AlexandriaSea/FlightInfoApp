package com.example.wenjiezhou_shengwang_comp304sec002_lab04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.databinding.FragmentDetailedScheduleBinding
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.viewmodels.AirScheduleViewModel
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.viewmodels.AirScheduleViewModelFactory
import kotlinx.coroutines.launch

class FragmentDetailedSchedule: Fragment() {

    companion object {
        var AIRLINE_NAME = "airlineName"
    }

    private var _binding: FragmentDetailedScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airlineName: String

    private val viewModel: AirScheduleViewModel by activityViewModels {
        AirScheduleViewModelFactory(
            (activity?.application as AirScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(AIRLINE_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewDetail
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val detailAdapter = AdapterDetail({})
        recyclerView.adapter = detailAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForAirlineName(airlineName).collect() {
                detailAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}