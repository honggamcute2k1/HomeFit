package com.example.fitnessproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmBroadCast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let { context ->
            showNotification(
                context = context,
                title = "Thông báo",
                body = "Đã đến giờ tập luyện"
            )
        }
    }

    private fun showNotification(context: Context, title: String, body: String) {
        val channelId = "Fitness"
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_clock)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_clock))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setVibrate(LongArray(0))
            .setContentTitle(title)
            .setContentText(body)
        val notificationManager =
            context.applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, builder.build())
    }
}