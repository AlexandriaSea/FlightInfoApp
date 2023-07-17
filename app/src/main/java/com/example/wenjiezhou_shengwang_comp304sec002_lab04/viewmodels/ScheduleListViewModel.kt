package com.example.wenjiezhou_shengwang_comp304sec002_lab04.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule.Schedule
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule.ScheduleDao

class AirScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForAirlineName(name: String): Flow<List<Schedule>> = scheduleDao.getByAirlineName(name)
}

class AirScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}