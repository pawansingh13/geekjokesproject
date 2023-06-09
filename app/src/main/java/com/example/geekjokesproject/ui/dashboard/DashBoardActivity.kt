package com.example.geekjokesproject.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.geekjokesproject.databinding.ActivityDashBoardBinding

import com.example.geekjokesproject.utill.AppConstants

import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  ActivityDashBoardBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

    }

}