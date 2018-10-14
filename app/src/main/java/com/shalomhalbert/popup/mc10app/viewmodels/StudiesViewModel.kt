package com.shalomhalbert.popup.mc10app.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.shalomhalbert.popup.mc10app.models.GetStudiesData
import com.shalomhalbert.popup.mc10app.models.Study
import com.shalomhalbert.popup.mc10app.repositories.MCTenRepository
import okhttp3.ResponseBody

class StudiesViewModel: ViewModel() {
    val studies =  MutableLiveData<Array<Study>>()
    val loggedOut = MutableLiveData<ResponseBody>()
    lateinit var getStudiesData: GetStudiesData

    fun fetchStudies() {
        MCTenRepository.getStudies(studies, getStudiesData)
    }

    fun logout() {
        MCTenRepository.logout(loggedOut, getStudiesData)
    }
}