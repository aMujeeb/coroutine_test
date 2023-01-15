package com.mujapps.coroutinetester.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mujapps.coroutinetester.databinding.FragmentLoginBinding
import com.mujapps.coroutinetester.view_model.LoginViewModel

class LoginFragment : Fragment() {

    private var _mLoginView: FragmentLoginBinding? = null
    private val mLoginView get() = _mLoginView!!

    private val mLoginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mLoginView = FragmentLoginBinding.inflate(inflater, container, false)
        return mLoginView.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mLoginView = null
    }
}