package com.desirecodes.pushnotificationtest.api

import com.desirecodes.pushnotificationtest.AccessToken
import com.desirecodes.pushnotificationtest.data.Notification
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    //POST https://fcm.googleapis.com/v1/projects/myproject-b5ae1/messages:send
    @POST("/v1/projects/notification-test-f29bc/messages:send")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    fun notification(
        @Body message : Notification,
        @Header("Authorization") accessToken: String = "Bearer ${AccessToken.getAccessToken()}"
    ):Call<Notification>
}