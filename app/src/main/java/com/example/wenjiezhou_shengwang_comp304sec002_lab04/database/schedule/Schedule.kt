package com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "airline_name") val airlineName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
    @NonNull @ColumnInfo(name = "terminal_number") val terminalNumber: String,
    @NonNull @ColumnInfo(name = "status") val status: String
)