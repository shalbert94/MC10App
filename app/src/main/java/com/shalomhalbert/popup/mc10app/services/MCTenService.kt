package com.shalomhalbert.popup.mc10app.services

import com.shalomhalbert.popup.mc10app.models.Authentication
import com.shalomhalbert.popup.mc10app.models.Study
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MCTenService {
    //Authenticate user given their email and accessToken
    @POST("user/login/email")
    @FormUrlEncoded
    fun authenticateUser(@Field("email") email: String,
                         @Field("password") password: String) : Call<Authentication>

    //Gets users studies
    @GET("accounts/{userAccountId}/studies")
    fun getStudies(@Path("userAccountId") accountId: String): Call<Array<Study>>

    //Log user out
    @POST("users/logout")
    fun logout(): Call<ResponseBody>
}