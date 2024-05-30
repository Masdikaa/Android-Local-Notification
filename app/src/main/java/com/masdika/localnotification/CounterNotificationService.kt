package com.masdika.localnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class CounterNotificationService(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counter: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE
        )
        val incrementIntent = PendingIntent.getBroadcast(
            context, 2, Intent(context, CounterNotificationReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.middle_finger)
            .setContentTitle("Increment Counter")
            .setContentText("The count is $counter")
            .setContentIntent(activityPendingIntent)
            .addAction(R.drawable.middle_finger, "Increment", incrementIntent)
            .build()

        notificationManager.notify(
            1, notification
        )
    }

    fun hideNotification(id: Int) {
        notificationManager.cancel(id)
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"

    }
}