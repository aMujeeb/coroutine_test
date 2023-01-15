package com.mujapps.coroutinetester.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mujapps.coroutinetester.databinding.ActivityRoomBinding
import com.mujapps.coroutinetester.view_model.RoomMainViewModel

class RoomActivity : AppCompatActivity() {

    private val mRoomViewBinding by lazy {
        ActivityRoomBinding.inflate(layoutInflater)
    }

    private val mRoomMainViewModel: RoomMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mRoomViewBinding.root)
    }
}