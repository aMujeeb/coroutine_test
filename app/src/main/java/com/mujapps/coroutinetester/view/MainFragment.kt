package com.mujapps.coroutinetester.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mujapps.coroutinetester.databinding.FragmentMainBinding
import com.mujapps.coroutinetester.view_model.MainFragmentViewModel

class MainFragment : Fragment() {

    private var _mMainFragmentBinding: FragmentMainBinding? = null
    private val mMainFragmentBinding get() = _mMainFragmentBinding!!

    private val mMainFragViewModel : MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mMainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mMainFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mMainFragmentBinding = null
    }
}