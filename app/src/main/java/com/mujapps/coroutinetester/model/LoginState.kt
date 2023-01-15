package com.mujapps.coroutinetester.model

object LoginState {
    var isLoggedIn = false
    var mUser: User? = null

    fun logOut() {
        isLoggedIn = false
        mUser = null
    }

    fun login(user: User) {
        isLoggedIn = true
        mUser = user
    }
}