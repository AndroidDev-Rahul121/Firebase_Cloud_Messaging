package com.example.fcmpushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServ  : FirebaseMessagingService() {

    // Override onNewToken to get new token
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("TAG", "From: " + message.from);

        val title = message.notification?.title
        val body  = message.notification?.body
sendNotification(title,body)

        if(message.notification !=null){
            Log.d("TAG", "Message Notification Body: " + message.notification!!.body);
            Log.d("TAG", "Message Notification Body: $title");
            Log.d("TAG", "Message Notification Body: $body");

        }
    }

    private fun sendNotification(title: String?, body: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "channel_id" // You can use any string for the channel ID

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel for Android Oreo and above
            val channel = NotificationChannel(channelId, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        // You can add additional customization to the notification, such as a click action, sound, etc.

        // Show the notification
        notificationManager.notify(0, notificationBuilder.build())
    }
}