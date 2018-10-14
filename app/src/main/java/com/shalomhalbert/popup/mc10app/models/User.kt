package com.shalomhalbert.popup.mc10app.models

import com.google.gson.annotations.Expose

/**
 * A user's credentials that are supplied to [Authentication]
 * Values are read-only
 */
data class User(@Expose val id: String,
                @Expose val accountId: String,
                @Expose val email: String,
                @Expose val firstName: String,
                @Expose val lastName: String,
                @Expose val locale:  String,
                @Expose val timezone: String,
                @Expose val legaleseVersionRequired: Int?,
                @Expose val legaleseVersionAccepted: Int?)