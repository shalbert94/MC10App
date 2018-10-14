package com.shalomhalbert.popup.mc10app.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.shalomhalbert.popup.mc10app.models.Authentication
import com.shalomhalbert.popup.mc10app.repositories.MCTenRepository

/**
 * [ViewModel] for [LoginFragment]
 */
class LoginViewModel: ViewModel() {
    //Holds authLiveData response
    val authLiveData = MutableLiveData<Authentication>()

    fun authenticateUser(username: String, password: String){
            MCTenRepository.authenticateUser(authLiveData, username, password)
    }
}