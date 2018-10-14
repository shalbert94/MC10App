package com.shalomhalbert.popup.mc10app.repositories

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.shalomhalbert.popup.mc10app.models.Authentication
import com.shalomhalbert.popup.mc10app.models.GetStudiesData
import com.shalomhalbert.popup.mc10app.models.Study
import com.shalomhalbert.popup.mc10app.services.MCTenService
import com.shalomhalbert.popup.mc10app.services.MCTenServiceGenerator
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Singleton repository used for making calls to https://qa.mc10cloud.com/api/v1/
 */
object MCTenRepository {

    private val TAG = MCTenRepository::class.java.simpleName

    /**
     * Posts an [Authentication] object to observed [liveData]
     */
    fun authenticateUser(liveData: MutableLiveData<Authentication>, username: String, password: String) {
        val mcTenService = MCTenServiceGenerator.createAuthService(MCTenService::class.java)
        mcTenService.authenticateUser(username, password).enqueue(object : Callback<Authentication> {
            override fun onResponse(call: Call<Authentication>, response: Response<Authentication>) {
                when (response.isSuccessful) {
                    true -> liveData.postValue(response.body())
                    false -> Log.e(TAG, "Request error body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Authentication>, t: Throwable) {
                Log.e(TAG, "User authLiveData failed")
                throw t
            }
        })
    }

    /**
     * Posts an array of [Study] objects to observed [liveData]
     */
    fun getStudies(liveData: MutableLiveData<Array<Study>>, studiesData: GetStudiesData) {
        val mcTenService = MCTenServiceGenerator
                .mainService(MCTenService::class.java, studiesData.userId, studiesData.accessToken)

        mcTenService.getStudies(studiesData.accountId).enqueue(object : Callback<Array<Study>> {
            override fun onResponse(call: Call<Array<Study>>, response: Response<Array<Study>>) {
                when (response.isSuccessful) {
                    true -> liveData.postValue(response.body())
                    false -> Log.e(TAG, "Request error body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Array<Study>>, t: Throwable) {
                //Throws UnknownHostException: Unable to resolve host “https://qa.mc10cloud.com” if you have poor internet connection
                Log.e(TAG, "Getting user studies failed")
                throw t
            }
        })
    }

    /**
     * Logs the user out
     */
    fun logout(liveData: MutableLiveData<ResponseBody>, studiesData: GetStudiesData) {
        val mcTenService = MCTenServiceGenerator
                .mainService(MCTenService::class.java, studiesData.userId, studiesData.accessToken)
        mcTenService.logout().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                when (response.isSuccessful) {
                    true -> liveData.postValue(response.body())
                    false -> Log.e(TAG, "Request error body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "Getting user studies failed")
                throw t
            }
        })
    }
}