package com.mujapps.coroutinetester.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    val signUpComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun singUp(userName: String, info: String) {

    }
}