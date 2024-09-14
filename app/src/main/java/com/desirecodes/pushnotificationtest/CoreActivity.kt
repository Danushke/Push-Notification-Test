package com.desirecodes.pushnotificationtest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.desirecodes.pushnotificationtest.data.CustomNotificationData
import com.google.gson.Gson

class CoreActivity : AppCompatActivity() {

    lateinit var textViewTitle: TextView
    lateinit var textViewBody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainn)

        textViewTitle = findViewById(R.id.tv_title)
        textViewBody = findViewById(R.id.tv_body)

        notificationHandle()

    }

    fun notificationHandle() {
        val mNotificationData =
            intent.getStringExtra(IntentParsableConstants.EXTRA_NOTIFICATION_DATA)
        mNotificationData?.let {
            try {
                // TODO if the notification has a custom data model use this (PCustomNotificationModel) to represent it
                Log.e("______****//**", it)

                val mCustomModel = it.jsonStringMapTo<CustomNotificationData>()
                    textViewTitle.text = mCustomModel.title
                    textViewBody.text = mCustomModel.body


                // TODO or else   use this default model
//                val mDefaultModel = it.jsonStringMapTo<PNotification>()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    inline fun <reified T : Any> String.jsonStringMapTo(): T =
        Gson().fromJson(this@jsonStringMapTo, T::class.java)
}