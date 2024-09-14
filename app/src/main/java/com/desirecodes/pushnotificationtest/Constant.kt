package com.desirecodes.pushnotificationtest

object Constant {

    const val PER_PAGE = 30
    const val CATEGORY_PER_PAGE = 20

    const val PREF_USER = "user_data"
    const val PREF_TOKEN = "access_token"
    const val ACTION_UNAUTH = "ACTION_UNAUTH"
    const val PREF_FCM_TOKEN = "fcm_push_token"

    const val APP_INFORMATION = "app_information"
    const val PRIVACY_POLICY = "PRIVACY_POLICY"
    const val TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS"
    const val ABOUT_US = "ABOUT_US"
    const val SUBSCRIPTION = "SUBSCRIPTION"
    const val FROM_BACKEND = "FROM_BACKEND"
    const val FROM_DEVICE = "FROM_DEVICE"

    const val GUEST = "GUEST"
    const val NON_GUEST = "NON_GUEST"
    const val LOGIN = "LOGIN"
    const val FACEBOOK = "FACEBOOK_URL"
    const val INSTAGRAM = "INSTAGRAM_URL"
    const val LINKEDIN = "LINKEDIN_URL"
    const val TWITTER = "TWITTER_URL"


    const val EXTRA_NOTIFICATION = "EXTRA_NOTIFICATION"
    const val EXTRA_NOTIFICATION_UUID = "notification_uuid"
    const val EXTRA_NOTIFICATION_TITLE = "notification_title"
    const val EXTRA_NOTIFICATION_MESSAGE = "notification_message"
    const val EXTRA_NOTIFICATION_TIME = "notification_time"

    const val PHOTO_LIST = 1
}

object IntentActionConstants {
    const val ACTION_VIEW_NOTIFICATIONS = "ACTION_VIEW_NOTIFICATIONS"
}

object ApiResponseCodes {
    const val UNAUTHORIZED = 401
    const val SERVER_ERROR = 500
}

object IntentParsableConstants {
    const val EXTRA_NOTIFICATION = "extra_notification"
    const val EXTRA_NOTIFICATION_DATA = "extra_notification_data"
    const val EXTRA_IS_FROM_LOGIN = "EXTRA_IS_FROM_LOGIN"
    const val EXTRA_STEP_SIGN_IN = "EXTRA_STEP_SIGN_IN"
    const val EXTRA_GUEST_SIGN_UP = "EXTRA_GUEST_SIGN_UP"
    const val EXTRA_EMAIL = "EXTRA_EMAIL"
    const val EXTRA_MAX_AGE = "EXTRA_MAX_AGE"
    const val EXTRA_MIN_AGE = "EXTRA_MIN_AGE"
    const val EXTRA_FILTER = "EXTRA_FILTER"
    const val EXTRA_ACCOUNT = "EXTRA_ACCOUNT"
    const val EXTRA_PROFILE = "EXTRA_PROFILE"
    const val EXTRA_PROFILEE = "EXTRA_PROFILEE"
    const val EXTRA_USER_ID = "EXTRA_USER_ID"
}
