package com.shalomhalbert.popup.mc10app.models

import com.google.gson.annotations.Expose

/**
 * POJO for authentication call response
 * Values are read-only
 */

data class Authentication(@Expose val user: User,
                          @Expose val accessToken: String,
                          @Expose val expiration: Long)