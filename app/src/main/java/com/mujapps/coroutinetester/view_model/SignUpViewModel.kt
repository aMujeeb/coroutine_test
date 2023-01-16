package com.mujapps.coroutinetester.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mujapps.coroutinetester.data.local.UserDataBase
import com.mujapps.coroutinetester.model.LoginState
import com.mujapps.coroutinetester.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val mCoroutineScope = CoroutineScope(Dispatchers.IO)
    private val mUserDao by lazy {
        UserDataBase(application).userDao()
    }

    val signUpComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun singUp(userName: String, password: String, info: String) {
        mCoroutineScope.launch {
            val user = mUserDao.getUser(userName)

            if (user != null) {
                withContext(Dispatchers.Main) {
                    error.value = "User Already Exists..!!!"
                }
            } else {
                val newUser = User(userName, password.hashCode(), info)
                val userId: Long = mUserDao.insertUser(newUser)
                newUser.id = userId
                LoginState.login(newUser)
                withContext(Dispatchers.Main) {
                    signUpComplete.value = true
                }
            }
        }
    }
}