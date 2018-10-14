package com.shalomhalbert.popup.mc10app.models

import com.google.gson.annotations.Expose
import java.text.SimpleDateFormat
import java.util.*

/**
 * POJO for GET studies call response
 * Values are read-only
 */

data class Study(@Expose val id: String,
                 @Expose val accountId: String,
                 @Expose val displayName: String,
                 @Expose val flags: List<Any>,
                 @Expose val tags: List<Any>,
                 @Expose val subjects: List<Any>,
                 @Expose val activities: List<Any>,
                 @Expose val diaries: List<Any>,
                 @Expose val deviceConfigs: List<Any>,
                 @Expose val pipelines: List<Any>,
                 @Expose val programs: List<Any>,
                 @Expose val title: String,
                 @Expose val isArchived: Boolean,
                 @Expose val createdTs: Long,
                 @Expose val lifecycleState: String) {

    private var studyDate: String? = null

    /**
     * @return  Date in the format we want to show a user
     */
    fun formattedDate(timeZone: TimeZone): String {
        //If studyDate was already calculated, don't recalculate
        if (studyDate != null) return studyDate!!

        if (createdTs == null) return ""

        val calendar = Calendar.getInstance()
        calendar.apply {
            timeInMillis = createdTs
            setTimeZone(timeZone)
        }
        val formatter = SimpleDateFormat("EEEE MMMM d, yyyy h:mm a")
        studyDate = formatter.format(calendar.time)
        return studyDate!!
    }
}