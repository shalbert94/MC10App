package com.shalomhalbert.popup.mc10app.extensions

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.FragmentActivity
import com.shalomhalbert.popup.mc10app.R
import com.shalomhalbert.popup.mc10app.models.GetStudiesData
import java.util.*

/**
 * Singleton for [SharedPreferences] interactions
 */
object SharedPrefExts

/* Store a value in Shared Preferences asynchronously */
fun SharedPrefExts.updateSharedPreferences(activity: FragmentActivity?, key: String, value: Any) {
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            ?: return //Ensures val is type SharedPreferences
    with(sharedPref.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Long -> putLong(key, value)
        }
        apply()
    }
}

/** Get users TimeZone from SharedPreferences */
fun SharedPrefExts.fetchTimezone(activity: FragmentActivity?): TimeZone {
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            ?: return TimeZone.getTimeZone("US/Eastern")

    val timeZone = sharedPref.getString(activity.getString(R.string.user_timezone), "")
    val timeZoneIds = TimeZone.getAvailableIDs()

    //Checks if the retrieved timezone is a valid TimeZone id
    return if (timeZoneIds.contains(timeZone)) TimeZone.getTimeZone(timeZone)
    else TimeZone.getTimeZone("US/Eastern")
}

/** Initializes a GetStudiesData object with shared preferences values*/
fun SharedPrefExts.fetchGetStudiesData(activity: FragmentActivity?): GetStudiesData {
    val empty = ""

    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            ?: return GetStudiesData(empty, empty, empty)

    val accountId = sharedPref.getString(activity.getString(R.string.user_account_id), empty)!!
    val userId = sharedPref.getString(activity.getString(R.string.user_id), empty)!!
    val accessToken = sharedPref.getString(activity.getString(R.string.access_token), empty)!!
    return GetStudiesData(accountId, userId, accessToken)
}