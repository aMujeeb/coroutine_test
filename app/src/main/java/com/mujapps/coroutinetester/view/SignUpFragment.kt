package com.mujapps.coroutinetester.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.mujapps.coroutinetester.databinding.FragmentSignUpBinding
import com.mujapps.coroutinetester.view_model.SignUpViewModel

class SignUpFragment : Fragment(), View.OnClickListener {

    private var _mSignUpView: FragmentSignUpBinding? = null
    private val mSignUpView get() = _mSignUpView!!

    private val mSignUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mSignUpView = FragmentSignUpBinding.inflate(inflater, container, false)
        return mSignUpView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSignUpView.mBtnNavigateToLogin.setOnClickListener(this)
        mSignUpView.mBtnSignUp.setOnClickListener(this)

        mSignUpViewModel.signUpComplete.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Sign up completed..!!!", Toast.LENGTH_SHORT)
                .show()
            onSignUp()
        }

        mSignUpViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error :$it", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mSignUpView = null
    }

    private fun onSignUp() {
        val action = SignUpFragmentDirections.actionSignUpFragmentToMainFragment()
        Navigation.findNavController(mSignUpView.mBtnSignUp).navigate(action)
    }

    private fun onNavigateToLogin(v: View) {
        val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onClick(p0: View?) {
        if (p0 == mSignUpView.mBtnNavigateToLogin) {
            onNavigateToLogin(p0)
        } else if (p0 == mSignUpView.mBtnSignUp) {
            //Sign up functionality
            val userName: String = mSignUpView.mTxtSignUserName.text.toString()
            val password: String = mSignUpView.mTxtSignPassword.text.toString()
            val otherInfo: String = mSignUpView.mTxtSignConfirmPassword.text.toString()
            if (userName.isNullOrEmpty() || password.isNullOrEmpty() || otherInfo.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please Fill all fields..!!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                mSignUpViewModel.singUp(userName, password)
            }
        }
    }
}