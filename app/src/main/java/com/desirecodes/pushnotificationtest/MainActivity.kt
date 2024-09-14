package com.desirecodes.pushnotificationtest

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.desirecodes.pushnotificationtest.data.Notification
import com.desirecodes.pushnotificationtest.data.NotificationData
import com.desirecodes.pushnotificationtest.network.NetworkClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    lateinit var etTitle: EditText
    lateinit var etBody: EditText
    lateinit var etToken: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.button)
        val testView = findViewById<TextView>(R.id.textView)
        etTitle = findViewById(R.id.editTextTitle)
        etBody = findViewById(R.id.editTextBody)
        etToken = findViewById(R.id.editTextToken)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        abc()
        testView.setOnClickListener {
//            FirebaseMessaging.getInstance().token

            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                testView.text = token
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })
        }
        sendButton.setOnClickListener {
            val title = etTitle.text.toString()
            val body = etBody.text.toString()
            val token = etToken.text.toString()
            if (token.isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter device push token",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                if (title.isEmpty() && body.isEmpty()) {
                    sendNotification(
                        "This is notification title",
                        "This is firebase v1 message",
                        token
                    )
                } else if (title.isEmpty()) {
                    sendNotification("This is notification title", body, token)
                } else if (body.isEmpty()) {
                    sendNotification(title, "This is firebase v1 message", token)
                } else
                    sendNotification(title, body, token)
            }

        }
    }

    fun abc() {
        FirebaseMessaging.getInstance().subscribeToTopic("test")
    }

    fun sendNotification(title: String, body: String, token: String) {
        val notification = Notification(
            message = NotificationData(
//                topic = "test",
                token = token,
                hashMapOf(
                    "title" to title,
                    "body" to body
                )
            )
        )
        NetworkClient.sendNotification().notification(notification).enqueue(
            object : retrofit2.Callback<Notification> {
                override fun onResponse(p0: Call<Notification>, p1: Response<Notification>) {
                    Toast.makeText(this@MainActivity, "$p0", Toast.LENGTH_LONG).show()
                    println("Success $p0")
                    println("Success $p1")
                }

                override fun onFailure(p0: Call<Notification>, p1: Throwable) {
                    Toast.makeText(this@MainActivity, "$p0", Toast.LENGTH_LONG).show()
                    println("Error $p0")
                    println("Error $p1")
                }

            }
        )
    }

    fun sendNotification2() {
        /*      // This registration token comes from the client FCM SDKs.
              val registrationToken = "YOUR_REGISTRATION_TOKEN"

      // See documentation on defining a message payload.

      // See documentation on defining a message payload.
              val message: Message = Message.builder()
                  .putData("score", "850")
                  .putData("time", "2:45")
                  .setToken(registrationToken)
                  .build()

      // Send a message to the device corresponding to the provided
      // registration token.

              val response: String = FirebaseMessaging.getInstance().send(message)
      // Response is a message ID string.
              println("Successfully sent message: $response")*/
    }
}