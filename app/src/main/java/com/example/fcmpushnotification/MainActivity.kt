package com.example.fcmpushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Get the FCM token
//        val token = FirebaseMessaging.getInstance().token;
//
//        // Print the token to the console
//        Log.d("FCM Token", token.toString());


//         Request FCM token
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.d("FCM", "FCM Token: $token")

                    // Do something with the token (e.g., send it to your server for push notifications)
                } else {
                    Log.d("FCM", "Failed to get FCM token: ${task.exception}")
                }
            }

        // Add token refresh listener
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val refreshedToken = task.result
                Log.d("FCM", "Refreshed FCM Token: $refreshedToken")

                // Do something with the refreshed token (e.g., update it on your server)
            } else {
                Log.d("FCM", "Failed to get refreshed FCM token: ${task.exception}")
            }
        }
//        FirebaseMessagingServ()
    }
}