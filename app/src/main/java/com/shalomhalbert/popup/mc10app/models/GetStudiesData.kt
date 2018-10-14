package com.shalomhalbert.popup.mc10app.models

/**
 * Contains user's data that's required for getting their studies
 */
data class GetStudiesData(var accountId: String,
                          var userId: String,
                          var accessToken: String)