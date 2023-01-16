package com.mujapps.coroutinetester.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mujapps.coroutinetester.data.local.UserDataBase
import com.mujapps.coroutinetester.model.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val signOutComplete = MutableLiveData<Boolean>()
    val userDeleted = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    private val mCoroutineScope = CoroutineScope(Dispatchers.IO)
    private val mUserDao by lazy {
        UserDataBase(application).userDao()
    }

    fun onDeleteUser() {
        mCoroutineScope.launch {
            try {
                LoginState.mUser?.let {
                    mUserDao.deleteUser(it.id)
                }

                withContext(Dispatchers.Main) {
                    LoginState.logOut()
                    userDeleted.value = true
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun onSignOutUser() {
        LoginState.logOut()
        signOutComplete.value = true
    }
}