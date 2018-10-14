package com.shalomhalbert.popup.mc10app.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Generates services for interacting with https://qa.mc10cloud.com/api/v1/
 */
object MCTenServiceGenerator {

    private const val BASE_URL = "https://qa.mc10cloud.com/api/v1/"

    private val retrofit =
            Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

    /** Creates a service for authenticating user */
    fun <S : MCTenService> createAuthService(service: Class<S>): S {
        return retrofit.build().create(service)
    }

    /** Creates a service for getting users studies or logging them out*/
    fun <S : MCTenService> mainService(service: Class<S>, userId: String, accessToken: String): S {
        val okHttpClient =
                OkHttpClient
                        .Builder()
                        .addInterceptor(MCTenInterceptor(userId, accessToken))
                        .build()

        retrofit.client(okHttpClient)

        return retrofit.build().create(service)
    }
}