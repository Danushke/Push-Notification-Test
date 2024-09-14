package com.desirecodes.pushnotificationtest

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.desirecodes.pushnotificationtest.IntentActionConstants.ACTION_VIEW_NOTIFICATIONS
import com.desirecodes.pushnotificationtest.IntentParsableConstants.EXTRA_NOTIFICATION
import com.desirecodes.pushnotificationtest.IntentParsableConstants.EXTRA_NOTIFICATION_DATA
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import java.util.Random

class MyFirebaseMessaging: FirebaseMessagingService() {

    private val CHANNEL_ID = "NotificationTest"
    private val CHANNEL_NAME = "Notifications"
    private var mContent: String? = null


    private val notificationManager:NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.let { message ->

//            mNotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setupNotificationChannels()
            }
            val notificationId = Random().nextInt(60000)

            mContent = message.data.toJsonString()

            var pendingIntent: PendingIntent? = null

            var intentActivity: Intent? = null

            if (applicationInForeground()) {
                // clear the top and launch the notification tab
                intentActivity = Intent(this, CoreActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    action = ACTION_VIEW_NOTIFICATIONS
                    putExtra(EXTRA_NOTIFICATION, true)
                    putExtra(EXTRA_NOTIFICATION_DATA, mContent)
                }
            } else {
                // Create a Navigation To launch the notification
                intentActivity = Intent(this, CoreActivity::class.java).apply {
                    action = ACTION_VIEW_NOTIFICATIONS
                    putExtra(EXTRA_NOTIFICATION, true)
                    putExtra(EXTRA_NOTIFICATION_DATA, mContent)
                }
            }
            pendingIntent = PendingIntent.getActivity(
                this,
                112,
                intentActivity,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(message.data["title"])
//                .setContentText(message.data["message"])
                .setContentText(message.data["body"])
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(
                notificationId,
                notificationBuilder.build()
            )
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupNotificationChannels() {
        val adminChannelName = "Test"
        val adminChannelDescription = "Description"

        val adminChannel: NotificationChannel

        adminChannel = NotificationChannel(
            CHANNEL_ID,
            adminChannelName,
            NotificationManager.IMPORTANCE_LOW
        )
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.RED
        adminChannel.enableVibration(true)
        notificationManager.createNotificationChannel(adminChannel)
    }


    private fun applicationInForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.runningAppProcesses
        var isActivityFound = false

        if (services[0].processName
                .equals(
                    packageName,
                    ignoreCase = true
                ) && services[0].importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
        ) {
            isActivityFound = true
        }

        return isActivityFound
    }

    fun Any.toJsonString(): String = Gson().toJson(this@toJsonString)
}