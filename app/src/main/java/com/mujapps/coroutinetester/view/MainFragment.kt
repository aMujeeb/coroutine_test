package com.mujapps.coroutinetester.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mujapps.coroutinetester.databinding.FragmentMainBinding
import com.mujapps.coroutinetester.view_model.MainFragmentViewModel

class MainFragment : Fragment(), View.OnClickListener {

    private var _mMainFragmentBinding: FragmentMainBinding? = null
    private val mMainFragmentBinding get() = _mMainFragmentBinding!!

    private val mMainFragViewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mMainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mMainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMainFragmentBinding.mBtnLogOut.setOnClickListener(this)
        mMainFragmentBinding.mBtnDeleteUser.setOnClickListener(this)

        mMainFragViewModel.signOutComplete.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Signed Out", Toast.LENGTH_SHORT)
                .show()
            navigateToLoginScreen(mMainFragmentBinding.mBtnLogOut)
        }

        mMainFragViewModel.userDeleted.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "User Deleted", Toast.LENGTH_SHORT)
                .show()
            navigateToLoginScreen(mMainFragmentBinding.mBtnDeleteUser)
        }

        mMainFragViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error :$it", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mMainFragmentBinding = null
    }

    override fun onClick(v: View?) {
        if (v == mMainFragmentBinding.mBtnLogOut) {
            mMainFragViewModel.onSignOutUser()
        } else if (v == mMainFragmentBinding.mBtnDeleteUser) {
            onDelete()
        }
    }

    private fun navigateToLoginScreen(v: View) {
        val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
        Navigation.findNavController(v).navigate(action)
    }

    private fun onDelete() {
        //This want make the App crash when activity is in Background
        activity?.let {
            AlertDialog.Builder(it).setTitle("Delete User")
                .setMessage(
                    "Are You sure you want to Delete the User?"
                )
                .setPositiveButton("Yes") { p0, p1 ->
                    mMainFragViewModel.onDeleteUser()
                }
                .setNegativeButton("No", null)
                .create()
                .show()
        }
    }
}