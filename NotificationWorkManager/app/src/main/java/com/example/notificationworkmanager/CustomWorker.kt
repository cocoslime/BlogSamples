package com.example.notificationworkmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class CustomWorker(private val context: Context, params: WorkerParameters) : Worker(context, params){

    override fun doWork(): Result {
        createNotificationChannel()

        val notification = createNotification(
            inputData.getString("title") ?: "Empty Title",
            inputData.getString("message") ?: ""
        )
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

        Log.d("CustomWorker", "doWork()")
        return Result.success()
    }

    private fun createNotificationChannel() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(NotificationChannel(
            CHANNEL_ID,
            "Channel Name",
            NotificationManager.IMPORTANCE_DEFAULT
        ))
    }

    private fun createNotification(title: String, message: String) : Notification {
        val intent = Intent(context, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, FLAG_IMMUTABLE)
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(androidx.appcompat.R.drawable.abc_ic_clear_material)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    companion object {
        const val NOTIFICATION_ID = 0
        const val CHANNEL_ID = "CHANNEL_ID"
    }
}