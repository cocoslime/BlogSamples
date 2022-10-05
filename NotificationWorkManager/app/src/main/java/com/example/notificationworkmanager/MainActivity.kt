package com.example.notificationworkmanager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createWorkRequest("message")
    }

    private fun createWorkRequest(message: String) {
        // https://developer.android.com/reference/androidx/work/PeriodicWorkRequest#MIN_PERIODIC_INTERVAL_MILLIS
        // 주기적 작업의 최소 반복 시간은 15분 입니다.
        val myWorkRequest = PeriodicWorkRequestBuilder<CustomWorker>(15, TimeUnit.MINUTES)
            .setInitialDelay(60, TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "Reminder",
                "message" to message,
            ))
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "name",
            ExistingPeriodicWorkPolicy.REPLACE,
            myWorkRequest
        )
    }
}