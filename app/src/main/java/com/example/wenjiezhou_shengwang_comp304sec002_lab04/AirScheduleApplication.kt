package com.example.wenjiezhou_shengwang_comp304sec002_lab04

import android.app.Application
import com.example.wenjiezhou_shengwang_comp304sec002_lab04.database.AppDatabase

class AirScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}