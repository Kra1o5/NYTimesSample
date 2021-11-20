package com.randomdroids.nytimes.data.server

object ServerConstants {
    const val URL = "https://api.nytimes.com/svc/mostpopular/v2/"
    const val API_PARAMETER = "api-key="
    const val ALL_SECTIONS = "all-sections"
    const val MOST_VIEWED = "mostviewed"
    const val MOST_EMAILED = "mostemailed"
    const val MOST_SHARED = "mostshared"
    const val LAST_DAY = "1.json"
    const val LAST_SEVEN_DAYS = "7.json"
    const val LAST_30_DAYS = "30.json"
    const val FACEBOOK = "facebook"
    const val TWITTER = "twitter"
    const val ALL_SOCIAL = "$FACEBOOK;$TWITTER"
}