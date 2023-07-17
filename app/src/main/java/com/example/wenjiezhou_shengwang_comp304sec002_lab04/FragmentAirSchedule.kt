package com.example.wenjiezhou_shengwang_comp304sec002_lab04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.databinding.FragmentAirScheduleBinding
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.viewmodels.AirScheduleViewModel
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.viewmodels.AirScheduleViewModelFactory
import kotlinx.coroutines.launch

class FragmentAirSchedule: Fragment() {

    private var _binding: FragmentAirScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: AirScheduleViewModel by activityViewModels {
        AirScheduleViewModelFactory(
            (activity?.application as AirScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewMain
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val mainAdapter = AdapterMain({
            val action = FragmentAirScheduleDirections.actionFragmentAirScheduleToFragmentDetailedSchedule(
                    airlineName = it.airlineName
                )
            view.findNavController().navigate(action)
        })
        recyclerView.adapter = mainAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                mainAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}