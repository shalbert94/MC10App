package com.shalomhalbert.popup.mc10app.services

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Statically modifies [MCTenServiceGenerator] header to include Basic Authentication.
 */
class MCTenInterceptor(private val userId: String, private val accessToken: String) : Interceptor {

    private val HEADER_AUTHORIZATION_NAME = "Authorization"

    private val HEADER_ACCEPT_NAME = "Accept"
    private val HEADER_ACCEPT_VALUE = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(userId, accessToken)

        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
                .addHeader(HEADER_AUTHORIZATION_NAME, credentials) //Basic auth
                .addHeader(HEADER_ACCEPT_NAME, HEADER_ACCEPT_VALUE)
                .build()
        return chain.proceed(authenticatedRequest)
    }
}