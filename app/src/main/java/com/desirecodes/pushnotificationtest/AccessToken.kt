package com.desirecodes.pushnotificationtest

import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

object AccessToken {
    private val firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging"
    fun getAccessToken(): String? {
        try {
            val jsonString = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"notification-test-f29bc\",\n" +
                    "  \"private_key_id\": \"0dab4fbd46eb336e23bcb8c32d84fec21b379134\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMtul+bGZgZPop\\nZwM8Vn5Eai9va3hRyPVMNWmJ6QdTnaXx/38vvUbMwFC2C7t5vTOQK+QwwYT0Daw9\\ni+tzoVWX1zIhXsZ5LlHhmq/kt68naTqJrdNpRjXgXM+dyhYGBEkfbvXju+8opjvl\\nIV/q9TYqaykVlk3clC2aNRklbedEbvvOc7gGxI3JQVhodfTS+/8SDp9YO1lh+s5w\\ni76S0HCkdq5KwYnUdxef2yhpiadmM+SkOL9WRV99uUtJSRHa7d0VYJxfJj5KPF8T\\nQ4qda/z+/PLMrmnehynldCgbk9MBMCEjoF91OIdRe9/t6S50fHevFObDYoiH4GMg\\n87GsYnSPAgMBAAECggEASsAdmYrGhTtJ93VEgrNIjv3BDWvONvrsyyOTwoZjJP6j\\nOi7jx9BS+m/c6OR20/ioy+OjAsSfIiid9KlTU9kc8oFjLjk3uscVog4EXT1PSVJM\\nzDo7QK0g/iiVrRW+HftRohc31SzqIss0grHBnv9kBHA1w8qq6rj2v+DUqkyrzlob\\nC9rNJRBQ+k6p8NzKOIQuIdfqmLHO7prLBv8RClMqjDhrclRNWM945voifsG0ttKT\\nl1xIHhLbEPSfbc2/HBM4v7bVBux6GKd97UIy/ggHF+ptHumoi2OerhCN8XWVcuHr\\nh7u2B38MNFzMr3gO+OtRsq5IJ1JzDcy9WI2FnbN30QKBgQDlxRdxEK6mJMCJ7f3G\\n6Kj//6GejAUbALIUx2neE7alT6ERd1CAusaLev/P6KVW1LvRKQ4vdStVDC9MNW+8\\nWuyswmqd6qXvDA7zrnBZyu2GscHsNz373qVDAsLM7TjQkBgkCzsX95kaj3SSBe/x\\ngieBRf0MDFfcavJ/UpsigOC5cQKBgQDkFZbpNsIzYBA+R3oGGG1AjVCBVuRPHg7D\\nlGN61+DYwhWgIlr8XnNgQ0UTgTzEvS/tP5ephepPzx66HFB0BFd3l6vIJU/VPB3/\\ne+i9CgZ3HYTUENrca41uu6IYjPcW05U+MxQMmnJdPJHshGsvRnc7L4jjL7nf0/6z\\nHNHmnY8N/wKBgQDM7TraEani7rZ2jjegNxO7tvAbqwBGtBkgJTbL5cMpr1qVbFf1\\naB/6eMo9UV5dkkCs7LQFKbhrEwIAM0yjrmiWMJPMomI6UQPo3HTB8RZl4pZ1qlNz\\nF4FHweehxBW/FjUhuI6M8ryvjrDGJ9t4B8BaMOTe4QdfxFbhrfhFDrLfUQKBgDxx\\n3ESB1oV299cJajUjVBC/Yo5kUK0G8jKyqIptm5DWHpUJhcNL5JemPDAnF8urt3/Q\\nUokCjE4KPcJkro2QeP2gnE22VanU/L4wQEWY2o3GrOTmcpnnhtvJrfODqBfMisip\\nEK8mXxiGbMWZci5dJkvxFP3gMAo5TE57XMUZDEhRAoGBALDyU/s+Uc5yWWvv8uS+\\no44HKUSSDIKRRZzUG98eCam09l3PgDRT8Z+qhQPChxZcsyzRkmiglSDNAGI2Dn/j\\nM5zm0IQDPTrYZaVfJfYd6azndoUV4BJ6kOudELeUg5pM7GXpCj2FIbuu7gx+bQBS\\ntKLdAsn/tbERoSdJqw6g4kNE\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-m9r09@notification-test-f29bc.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"116266388342495927357\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-m9r09%40notification-test-f29bc.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}\n"

            val stream = ByteArrayInputStream(jsonString.toByteArray(StandardCharsets.UTF_8))
            val googleCredential = GoogleCredentials.fromStream(stream)
                .createScoped(arrayListOf(firebaseMessagingScope)
            )

            googleCredential.refresh()
            return googleCredential.accessToken.tokenValue
        } catch (e: IOException) {
            return null
        }
    }
}