package com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE airline_name = :airlineName ORDER BY arrival_time ASC")
    fun getByAirlineName(airlineName: String): Flow<List<Schedule>>
}