package com.desirecodes.pushnotificationtest.network

import com.desirecodes.pushnotificationtest.api.NotificationAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private var retrofit: Retrofit? = null

    fun sendNotification(): NotificationAPI {
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder()
                    .baseUrl("https://fcm.googleapis.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!.create(NotificationAPI::class.java)
    }
}